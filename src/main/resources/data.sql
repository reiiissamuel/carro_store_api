CREATE TABLE carro (
    id INT IDENTITY,
    marca VARCHAR (50) NOT NULL,
    modelo VARCHAR (50) NOT NULL,
    data_cadastro DATE,
    valor DECIMAL(10,2),
    PRIMARY KEY (id)
);