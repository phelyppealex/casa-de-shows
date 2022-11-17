CREATE TABLE IF NOT EXISTS Cliente (
	id serial,
	nome text,
	cpf text, 
	telefone text,
	email text,
	login text,
	senha text,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Evento (
	id serial,
	nomeEvento text,
	data_event text,
	hora text,
	capacidade integer,
	rua text,
	numero integer,
	bairro text,
	cidade text,
	UF text,
	preco decimal,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Pessoa (
	id serial,
	nome text,
	cpf text,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Ingresso (
	id serial,
	fk_evento int,
	fk_pessoa int,
	FOREIGN KEY (fk_evento) REFERENCES Evento(id),
	FOREIGN KEY (fk_pessoa) REFERENCES Pessoa(id)
);

CREATE TABLE IF NOT EXISTS ClienteEvento (
	fk_cliente int,
	fk_evento int,
	FOREIGN KEY (fk_cliente) REFERENCES Cliente(id),
	FOREIGN KEY (fk_evento) REFERENCES Evento(id),
	PRIMARY KEY (fk_cliente, fk_evento)
);