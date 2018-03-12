INSERT INTO PaginaAcoesVisitor (PaginaAcoesVisitor_type, id, version, description) VALUES ('PaginaGenenericaAcoesVisitor', 1,0, 'yghjgjhgjhgh');
INSERT INTO PaginaAcoesVisitor (PaginaAcoesVisitor_type, id, version, description) VALUES ('TestingDecoratorsAcoesVisitor', 2,0, 'hjythhfghfg');
INSERT INTO PaginaAcoesVisitor (PaginaAcoesVisitor_type, id, version, description) VALUES ('ZimbraLoginAcoesVisitor', 3,0, 'aaaaaaaaaaaaaa');
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (1, 0, 'P1E1','P1E1','@DSDSD', 1);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (2, 0, 'P1E2','P1E2','@DSDSD', 1);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (3, 0, 'P1E3','P1E3','@DSDSD', 1);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (4, 0, 'P2E1','P2E1','@DSDSD', 2);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (5, 0, 'Página Webmail Zimbra','Página Webmail Zimbra','http://webmail.cge.ce.gov.br/zimbra/', 3);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (6, 0, 'gtreinamento','USER','//input[@id=''username'']', 3);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (7, 0, 'Treina123','PASSWORD','//input[@id=''password'']', 3);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (8, 0, 'Botao login','Botao login','//input[@value=''Login'']', 3);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (9, 0,
'Email de Confirmação de Solicitação de Cadastro',
'Email de Confirmação de Solicitação de Cadastro',
'//tr/td[contains(text(), ''Email de Confirmação de Solicitação de Cadastro'')]', 3);
INSERT INTO Elemento (id, version, description, nome, xpath, visitor_id) VALUES (10, 0,
'Link de Confirmação de Solicitação de Cadastro',
'Link de Confirmação de Solicitação de Cadastro',
'//a[starts-with(@href, ''http://localhost:8080/e-parcerias-web/paginas/parceiro/'')]', 3);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 1, 0, 'asdsdas', 1);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoPreencherCampo', 2, 0, 'asdsdas', 1);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 3, 0, 'asdsdas', 2);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoPreencherCampo', 4, 0, 'asdsdas', 2);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 5, 0, 'asdsdas', 3);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoPreencherCampo', 6, 0, 'asdsdas', 3);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 7, 0, 'asdsdas', 4);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoPreencherCampo', 8, 0, 'asdsdas', 4);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoIrPara', 9, 0, 'Ir para página webmail zimbra', 5);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoPreencherCampo', 10, 0, 'Preencher usuário', 6);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoPreencherCampo', 11, 0, 'Preencher senha', 7);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 12, 0, 'clica no botão de login', 8);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 13, 0, 'clica no Email de Confirmação de Solicitação de Cadastro', 9);
INSERT INTO Acao (Acao_type, id, version, description, elemento_id) VALUES ('AcaoClicarBotao', 14, 0, 'clica no Link de Confirmação de Solicitação de Cadastro', 10);

