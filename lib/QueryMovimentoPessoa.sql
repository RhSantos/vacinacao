SELECT
	movimento_estoque.movimento,
	uni.unidade,
	uni.nome AS uniNome,
	uni.centro,
	uniEndereco.endereco AS uniEndereco,
	uniEndereco.logradouro AS uniLogradouro,
	uniEndereco.cidade AS uniCidade,
	uniEndereco.estado AS uniEstado,
	uniEndereco.numero AS uniNumero,
	uniEndereco.bairro AS uniBairro,
	uniEndereco.complemento AS uniComplemento,
	uniEndereco.cep AS uniCep,
	lote.lote,
	lote.nome,
	lote.data_vencimento,
	pessoa.pessoa,
	pessoa.nome AS pesNome,
	pessoa.cpf,
	pesEndereco.endereco AS pesEndereco,
	pesEndereco.logradouro AS pesLogradouro,
	pesEndereco.cidade AS pesCidade,
	pesEndereco.estado AS pesEstado,
	pesEndereco.numero AS pesNumero,
	pesEndereco.bairro AS pesBairro,
	pesEndereco.complemento AS pesComplemento,
	pesEndereco.cep AS pesCep,
	movimento_estoque.quantidade as movQuant,
	movimento_estoque.tipo_movimento,
	movimento_estoque.tipo_transacao,
	movimento_estoque.data_movimento
FROM
	movimento_estoque
	INNER JOIN cadastro_unidade as uni ON movimento_estoque.unidade = uni.unidade
	INNER JOIN endereco as uniEndereco ON uni.endereco = uniEndereco.endereco
	INNER JOIN lote ON movimento_estoque.lote = lote.lote
	INNER JOIN cadastro_pessoa as pessoa ON movimento_estoque.pessoa = pessoa.pessoa
	INNER JOIN endereco as pesEndereco ON pessoa.endereco = pesEndereco.endereco