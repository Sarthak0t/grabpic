CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE images (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    filename VARCHAR(255) NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_images_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE collections (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_collections_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE collection_images (
    collection_id BIGINT NOT NULL,
    image_id BIGINT NOT NULL,
    PRIMARY KEY (collection_id, image_id),
    CONSTRAINT fk_collection_images_collection
        FOREIGN KEY (collection_id)
        REFERENCES collections(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_collection_images_image
        FOREIGN KEY (image_id)
        REFERENCES images(id)
        ON DELETE CASCADE
);
