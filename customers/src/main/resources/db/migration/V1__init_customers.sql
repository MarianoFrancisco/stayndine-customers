CREATE TABLE IF NOT EXISTS customer (
    id               BINARY(16)      NOT NULL,
    email            VARCHAR(180)    NOT NULL,
    first_name       VARCHAR(100)    NOT NULL,
    last_name        VARCHAR(100)    NOT NULL,
    phone            VARCHAR(32)     NULL,
    birth_date       DATE            NULL,
    preferences_json JSON            NULL,
    created_at       TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at       TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT uq_customer_email UNIQUE (email)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS customer_address (
    id           BINARY(16)  NOT NULL,
    customer_id  BINARY(16)  NOT NULL,
    type         ENUM('HOME','BILLING','SHIPPING','OTHER') NOT NULL,
    country      VARCHAR(60)  NOT NULL,
    state        VARCHAR(60)  NULL,
    city         VARCHAR(80)  NOT NULL,
    postal_code  VARCHAR(20)  NULL,
    line1        VARCHAR(160) NOT NULL,
    line2        VARCHAR(160) NULL,
    is_default   BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT pk_customer_address PRIMARY KEY (id),
    KEY idx_cust (customer_id),
    CONSTRAINT fk_addr_customer
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
