CREATE TABLE guest
(
    id bigserial PRIMARY KEY,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    phone varchar(15),
    created_date TIMESTAMP WITH TIME ZONE
)