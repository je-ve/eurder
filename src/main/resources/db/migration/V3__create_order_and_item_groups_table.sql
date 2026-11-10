CREATE TABLE orders (
                        id VARCHAR(255) PRIMARY KEY,
                        customer_id VARCHAR(255) NOT NULL,
                        total_price DECIMAL(10, 2) NOT NULL,

                        CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES users(id)
);
CREATE TABLE item_groups (
                             id VARCHAR(255) PRIMARY KEY,
                             order_id VARCHAR(255) NOT NULL,
                             item_id VARCHAR(255) NOT NULL,
                             name VARCHAR(255) NOT NULL,
                             amount INT NOT NULL,
                             price_per_unit DECIMAL(10, 2) NOT NULL,
                             shipping_date DATE NOT NULL,
                             CONSTRAINT fk_item_group_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                             CONSTRAINT fk_item_group_item FOREIGN KEY (item_id) REFERENCES items(id)
);