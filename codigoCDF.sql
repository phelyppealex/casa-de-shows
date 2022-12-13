CREATE TABLE IF NOT EXISTS Cliente (
	cpf text,
	nome text, 
	telefone text,
	email text,
	senha text,
	PRIMARY KEY (cpf)
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
	cpf text,
	nome text,
	PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS Ingresso (
	id serial,
	fk_evento int,
	fk_pessoa text,
	FOREIGN KEY (fk_evento) REFERENCES Evento(id),
	FOREIGN KEY (fk_pessoa) REFERENCES Pessoa(cpf),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ClienteEvento (
	fk_cliente text,
	fk_evento int,
	FOREIGN KEY (fk_cliente) REFERENCES Cliente(cpf),
	FOREIGN KEY (fk_evento) REFERENCES Evento(id),
	PRIMARY KEY (fk_cliente, fk_evento)
);
