-- transform data from rows into columns to get a summarized view

-- part of the tablefunc extension
CREATE EXTENSION IF NOT EXISTS tablefunc;

-- takes in two queries
SELECT * FROM crosstab(
  'SELECT row_identifier, column_identifier, value_column FROM your_table ORDER BY 1, 2',
  'SELECT DISTINCT column_identifier FROM your_table ORDER BY 1'
) AS result(row_identifier type, column1 type, column2 type, ...);

-- row identifier: what you are summarizing (grouping data criteria)
-- column identifier: what the new columns will be
-- value column: what the table should be populated with

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255),
    quantity INT,
    date DATE
);

INSERT INTO orders (product_name,quantity,date) VALUES 
('Product A', 10, '2025-01-10'),
('Product B', 15, '2025-01-15'),
('Product A', 10, '2025-05-16'),
('Product C', 5, '2025-02-12'),
('Product B', 20, '2025-03-01'),
('Product A', 25, '2025-04-20'),
('Product C', 8, '2025-02-28'),
('Product B', 18, '2025-06-10'),
('Product A', 12, '2025-07-15'),
('Product C', 10, '2025-08-05');

SELECT * FROM orders;

CREATE TEMP TABLE temp_pivot AS
SELECT * FROM crosstab(
  'SELECT TO_CHAR(date, ''Mon YYYY'') AS month, product_name, SUM(quantity) 
   FROM orders 
   GROUP BY TO_CHAR(date, ''Mon YYYY''), product_name 
   ORDER BY 1, 2',
  'SELECT DISTINCT product_name FROM orders ORDER BY 1'
) AS result(
  month varchar, 
  "Product A" int, 
  "Product B" int, 
  "Product C" int
);

-- unpivot
SELECT month, 'Product A' AS product_name, "Product A" AS quantity
FROM temp_pivot
UNION ALL
SELECT month, 'Product B' AS product_name, "Product B" AS quantity
FROM temp_pivot
UNION ALL
SELECT month, 'Product C' AS product_name, "Product C" AS quantity
FROM temp_pivot;

-- pivoting by quarter
SELECT * FROM crosstab(
  'SELECT TO_CHAR(date, ''YYYY-Q'') AS quarter, product_name, SUM(quantity) 
   FROM orders 
   GROUP BY TO_CHAR(date, ''YYYY-Q''), product_name 
   ORDER BY 1, 2',
  'SELECT DISTINCT product_name FROM orders ORDER BY 1'
) AS result(
  quarter varchar, 
  "Product A" int, 
  "Product B" int, 
  "Product C" int
);

--pivoting by week
SELECT * FROM crosstab(
  'SELECT TO_CHAR(date, ''YYYY-WW'') AS week, product_name, SUM(quantity) 
   FROM orders 
   GROUP BY TO_CHAR(date, ''YYYY-WW''), product_name 
   ORDER BY 1, 2',
  'SELECT DISTINCT product_name FROM orders ORDER BY 1'
) AS result(
  week varchar, 
  "Product A" int, 
  "Product B" int, 
  "Product C" int
);



