CREATE USER carcas WITH PASSWORD 'carcassonne1';
CREATE DATABASE IF NOT EXISTS shopping_cart_localization;

\c shopping_cart_localization;




CREATE TABLE IF NOT EXISTS cart_records (
    cart_record_id SERIAL PRIMARY KEY,
    total_items INT NOT NULL,
    total_cost DOUBLE NOT NULL,
    language VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart_items (
    cart_item_id SERIAL PRIMARY KEY,
    cart_record_id INT,
    item_number INT NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (cart_record_id) REFERENCES cart_records(cart_recordid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS translations (
    translation_id SERIAL PRIMARY KEY,
    key VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(2) NOT NULL
);

-- English (en)
INSERT INTO translations (key, value, language) VALUES
('itemNumberPrompt', 'Enter the number of items:', 'en'),
('itemPricePrompt', 'Enter the price for item:', 'en'),
('itemQuantityPrompt', 'Enter the quantity for item:', 'en'),
('totalCostMessage', 'Total cost:', 'en'),
('checkOutPrompt', 'Enter 0 to check out:', 'en'),
('addItemPrompt', 'Add to total:', 'en'),
('calcItemPrompt', 'Calculate Total', 'en');

-- Japanese (ja)
INSERT INTO translations (key, value, language) VALUES
('itemNumberPrompt', '購入する商品の数を入力してください:', 'ja'),
('itemPricePrompt', '商品の価格を入力してください:', 'ja'),
('itemQuantityPrompt', '商品の数量を入力してください:', 'ja'),
('totalCostMessage', '合計金額:', 'ja'),
('checkOutPrompt', '0を入力して決済してください：', 'ja'),
('addItemPrompt', '合計に追加：', 'ja'),
('calcItemPrompt', '合計を計算する', 'ja');

-- Arabic (ar)
INSERT INTO translations (key, value, language) VALUES
('itemNumberPrompt', 'لخدأ ددع رصانعلا:', 'ar'),
('itemPricePrompt', 'لخدأ رعس رصنعلا:', 'ar'),
('itemQuantityPrompt', 'لخدأ ةيمك رصنعلا:', 'ar'),
('totalCostMessage', 'ةيلامجلإا ةفلكتلا:', 'ar'),
('checkOutPrompt', 'أدخل الرقم 0 لإتمام عملية الدفع:', 'ar'),
('addItemPrompt', 'أضف إلى المجموع:', 'ar'),
('calcItemPrompt', 'حساب المجموع', 'ar');

-- Finnish (fi)
INSERT INTO translations (key, value, language) VALUES
('itemNumberPrompt', 'Syötä ostettavien tuotteiden määrä:', 'fi'),
('itemPricePrompt', 'Syötä tuotteen hinta:', 'fi'),
('itemQuantityPrompt', 'Syötä tuotteen määrä:', 'fi'),
('totalCostMessage', 'Kokonaishinta:', 'fi'),
('checkOutPrompt', 'Syötä 0 lopettaaksesi:', 'fi'),
('addItemPrompt', 'Add to total:', 'fi'),
('calcItemPrompt', 'Laske hinta', 'fi');

-- Swedish (sv)
INSERT INTO translations (key, value, language) VALUES
('itemNumberPrompt', 'Ange antalet varor att köpa:', 'sv'),
('itemPricePrompt', 'Ange priset för varan:', 'sv'),
('itemQuantityPrompt', 'Ange mängden varor:', 'sv'),
('totalCostMessage', 'Total kostnad:', 'sv'),
('checkOutPrompt', 'Ange 0 för att checka ut:', 'sv'),
('addItemPrompt', 'lägga till totalt', 'sv'),
('calcItemPrompt', 'Beräkna totalt', 'sv');


GRANT ALL PRIVILEGES ON DATABASE carcassonne TO carcas;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO carcas;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO carcas;
