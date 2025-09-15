SET @now = CURRENT_TIMESTAMP(6);

INSERT INTO customer (id, email, first_name, last_name, phone, birth_date, preferences_json, created_at, updated_at)
VALUES (
           UUID_TO_BIN('3b1df2a1-0b49-4e7d-9a42-7d9e2fe3f0a1'),
           'alice@example.com',
           'Alice',
           'Rivera',
           '50212345678',
           '1996-05-17',
           JSON_OBJECT('newsletter', TRUE, 'language', 'es'),
           @now, @now
       )
    ON DUPLICATE KEY UPDATE
                         first_name = VALUES(first_name),
                         last_name  = VALUES(last_name),
                         phone      = VALUES(phone),
                         birth_date = VALUES(birth_date),
                         preferences_json = VALUES(preferences_json),
                         updated_at = @now;

INSERT INTO customer (id, email, first_name, last_name, phone, birth_date, preferences_json, created_at, updated_at)
VALUES (
           UUID_TO_BIN('6f2c6d9e-3a9a-4a6d-8a3f-0b7f3c2d1e4f'),
           'bob@example.com',
           'Bob',
           'Lopez',
           '50255500000',
           '1992-11-03',
           JSON_OBJECT('newsletter', FALSE, 'language', 'en'),
           @now, @now
       )
    ON DUPLICATE KEY UPDATE
                         first_name = VALUES(first_name),
                         last_name  = VALUES(last_name),
                         phone      = VALUES(phone),
                         birth_date = VALUES(birth_date),
                         preferences_json = VALUES(preferences_json),
                         updated_at = @now;

INSERT INTO customer_address (id, customer_id, type, country, state, city, postal_code, line1, line2, is_default)
SELECT
    UUID_TO_BIN('11111111-1111-1111-1111-111111111111'),
    c.id,
    'HOME',
    'Guatemala',
    'Guatemala',
    'Ciudad de Guatemala',
    '01010',
    'Zona 10, Calle 5',
    NULL,
    TRUE
FROM customer c
WHERE c.email = 'alice@example.com'
    ON DUPLICATE KEY UPDATE
                         country = VALUES(country),
                         state   = VALUES(state),
                         city    = VALUES(city),
                         postal_code = VALUES(postal_code),
                         line1   = VALUES(line1),
                         line2   = VALUES(line2),
                         is_default = VALUES(is_default);

INSERT INTO customer_address (id, customer_id, type, country, state, city, postal_code, line1, line2, is_default)
SELECT
    UUID_TO_BIN('22222222-2222-2222-2222-222222222222'),
    c.id,
    'HOME',
    'Guatemala',
    'Sacatepéquez',
    'Antigua Guatemala',
    '03001',
    '5a Avenida Norte',
    'Casa #12',
    TRUE
FROM customer c
WHERE c.email = 'bob@example.com'
    ON DUPLICATE KEY UPDATE
                         country = VALUES(country),
                         state   = VALUES(state),
                         city    = VALUES(city),
                         postal_code = VALUES(postal_code),
                         line1   = VALUES(line1),
                         line2   = VALUES(line2),
                         is_default = VALUES(is_default);
