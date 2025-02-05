CREATE TABLE employee_sales(
	employee_id SERIAL PRIMARY KEY,
	employee_name VARCHAR,
	work_month DATE,
	total_sales INT,
	num_clients INT,
	revenue_generated FLOAT
);

INSERT INTO employee_sales (employee_name, work_month, total_sales, num_clients, revenue_generated)
VALUES
    ('Alice', '2025-01-01', 50, 10, 5000),
    ('Bob', '2025-01-01', 30, 8, 3200),
    ('Charlie', '2025-01-01', 45, 9, 4500),
    ('Alice', '2025-02-01', 60, 12, 6000),
    ('Bob', '2025-02-01', 40, 10, 4000),
    ('Charlie', '2025-02-01', 55, 11, 5500),
    ('Alice', '2025-03-01', 70, 15, 7000),
    ('Bob', '2025-03-01', 50, 12, 5000);

SELECT * FROM employee_sales;

SELECT * FROM crosstab(
  'SELECT employee_name, TO_CHAR(work_month, ''Mon YYYY'') AS month, SUM(revenue_generated) 
   FROM employee_sales 
   GROUP BY employee_name, TO_CHAR(work_month, ''Mon YYYY'')
   ORDER BY 1, 2',
  'SELECT DISTINCT TO_CHAR(work_month, ''Mon YYYY'') AS month FROM employee_sales ORDER BY 1'
) AS result(
  employee_name VARCHAR, 
  "Jan 2025" INT, 
  "Feb 2025" INT, 
  "Mar 2025" INT
);

