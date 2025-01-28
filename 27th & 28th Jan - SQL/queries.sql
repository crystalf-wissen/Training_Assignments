-- Create an Enum for Designation
CREATE TYPE designation_enum AS ENUM ('Programmer', 'Tester', 'Admin', 'Manager','Clerk');

-- Create a table employees
CREATE TABLE employees(
	eid SERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age SMALLINT,
	gender VARCHAR(15) NOT NULL,
	salary NUMERIC(8, 2) DEFAULT 15000.00,
	designation designation_enum NOT NULL,
	email_id VARCHAR(255) NOT NULL,
	married BOOLEAN NOT NULL,
	joining_date DATE DEFAULT CURRENT_DATE,
	manager_id int,
	CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES employees (eid),
	CONSTRAINT check_email_id CHECK(email_id ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
	CONSTRAINT check_age CHECK(age>=21 AND age<=60),
	CONSTRAINT check_gender CHECK(gender IN ('Male', 'Female'))
);

-- Insert into employees table
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date)
VALUES ('Alice Johnson', 35, 'Female', 25000.00, 'Manager', 'alice.johnson@example.com', TRUE, '2025-01-01');
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
VALUES ('Bob Smith', 28, 'Male', 20000.00, 'Tester', 'bob.smith@example.com', FALSE, '2025-01-10', 1);
 
INSERT INTO employees (name, age, gender, salary, designation, email_id, married, joining_date, manager_id)
VALUES
	('Bob Smith', 28, 'Male', 25000.00, 'Programmer', 'bob.smith@example.com', FALSE, CURRENT_DATE, 1),
    ('Charlie Brown', 32, 'Male', 22000.00, 'Tester', 'charlie.brown@example.com', TRUE, CURRENT_DATE, 1),
    ('Diana White', 30, 'Female', 26000.00, 'Programmer', 'diana.white@example.com', FALSE, CURRENT_DATE, 1),
    -- Admin Reporting to Alice
    ('Eve Green', 35, 'Female', 28000.00, 'Admin', 'eve.green@example.com', TRUE, CURRENT_DATE, 1),
    -- New Manager (No Manager Reference)
    ('Frank Black', 45, 'Male', 35000.00, 'Manager', 'frank.black@example.com', TRUE, CURRENT_DATE, NULL),
    -- Employees Reporting to Frank (Manager with eid = 6)
    ('Grace Adams', 27, 'Female', 24000.00, 'Tester', 'grace.adams@example.com', FALSE, CURRENT_DATE, 6),
    ('Henry Ford', 29, 'Male', 23000.00, 'Programmer', 'henry.ford@example.com', FALSE, CURRENT_DATE, 6),
    ('Ivy Nelson', 31, 'Female', 27000.00, 'Admin', 'ivy.nelson@example.com', TRUE, CURRENT_DATE, 6),
    -- Another Tester Reporting to Bob (eid = 2)
    ('Jack Brown', 26, 'Male', 21000.00, 'Tester', 'jack.brown@example.com', FALSE, CURRENT_DATE, 2);

-- Create a new table role
CREATE TABLE role (
    designation designation_enum PRIMARY KEY,
    max_limit INTEGER,
    min_salary INTEGER
);

-- Insert values into table role
insert into role values
('Tester', 100, 20000),
('Programmer', 200, 30000),
('Manager', 15, 120000),
('Clerk', 50, 25000);

-- Find employees with salary less than minimum
SELECT * 
FROM employees EMP
INNER JOIN role R ON EMP.designation = R.designation
WHERE EMP.salary != R.min_salary;

-- Find out the employees who are earning more salary than their managers
SELECT e1.eid AS emp_id, 
       e1.name AS emp_name, 
       e1.salary AS emp_salary, 
       e2.eid AS manager_id, 
       e2.name AS manager_name, 
       e2.salary AS manager_salary
FROM employees e1
JOIN employees e2 ON e1.manager_id = e2.eid
WHERE e1.salary > e2.salary;

-- Find the total salary and rename the null row
select coalesce(designation::text, 'Total') as designation, sum(salary) from employees 
group by rollup(designation)
order by sum(salary);

-- PLSQL to insert employee using procedure 
CREATE OR REPLACE PROCEDURE insert_emp_record(
    e_name VARCHAR,
    e_age INT,
    e_gender VARCHAR, 
    e_salary NUMERIC(8, 2),
    e_designation VARCHAR, 
    e_email_id VARCHAR,
    e_married BOOLEAN, 
    e_manager_id INT 
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO employees(
        name, age, gender, salary, designation, email_id, married, joining_date, manager_id
    )
    VALUES (
        e_name, e_age, e_gender, e_salary, e_designation, e_email_id, e_married, CURRENT_DATE, e_manager_id
    );
END
$$;
call insert_emp_record()

-- Procedure for employee appraisal
CREATE OR REPLACE PROCEDURE appraisal(
	id int,
	amount int
)
LANGUAGE plpgsql
AS $$
BEGIN
	UPDATE employees SET salary=salary+amount WHERE eid=id;
end
$$;

SELECT * FROM employees;

call appraisal(13,15000)

-- PLSQL to determine the max salary
CREATE OR REPLACE FUNCTION max_salary(desig VARCHAR)
RETURNS TABLE(eid INT, name VARCHAR, salary DECIMAL)
AS $$
BEGIN
    RETURN QUERY
    SELECT e.eid, e.name, e.salary
    FROM employees e
    WHERE e.designation = desig::designation_enum
    AND e.salary = (
        SELECT MAX(e2.salary)
        FROM employees e2 
        WHERE e2.designation = desig::designation_enum
    );
END;
$$ LANGUAGE plpgsql;

SELECT * FROM max_salary('Manager');

SELECT 	* FROM employees e
WHERE e.salary > ( select AVG(salary) FROM employees 
GROUP BY designation 
HAVING designation =e.designation);


CREATE OR REPLACE FUNCTION validate_salary()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.salary < 12000 THEN
        NEW.salary := 12000;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


create trigger before_updating_salary
before update on employees
for each row
execute function validate_salary();

update employees set salary = salary - 40000 where eid=13;