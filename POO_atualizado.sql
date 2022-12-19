CREATE TABLE IF NOT EXISTS Cliente (
	cpf text PRIMARY KEY,
	nome text, 
	telefone text,
	email text,
	senha text
);

CREATE TABLE IF NOT EXISTS Evento (
	id serial PRIMARY KEY,
	nomeEvento text,
	data_event text,
	hora text,
	capacidade integer,
	rua text,
	numero integer,
	bairro text,
	cidade text,
	UF text,
	preco decimal
);

CREATE TABLE IF NOT EXISTS Pessoa (
	cpf text PRIMARY KEY,
	nome text
);

CREATE TABLE IF NOT EXISTS Ingresso (
	id serial PRIMARY KEY,
	fk_evento int,
	fk_pessoa text,
	FOREIGN KEY (fk_evento) REFERENCES Evento(id) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (fk_pessoa) REFERENCES Pessoa(cpf) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ClienteEvento (
	fk_cliente text,
	fk_evento int,
	FOREIGN KEY (fk_cliente) REFERENCES Cliente(cpf)  ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (fk_evento) REFERENCES Evento(id)  ON DELETE SET NULL ON UPDATE CASCADE,
	PRIMARY KEY (fk_cliente, fk_evento)
);

