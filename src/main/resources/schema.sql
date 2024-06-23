CREATE TABLE IF NOT EXISTS public.users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255)
);
