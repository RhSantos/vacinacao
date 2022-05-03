SELECT
	movimento_estoque.movimento,
	uni.unidade,
	uni.nome,
	uni.centro,
	uniEndereco.*,
	lote.*,
	pessoa.pessoa,
	pessoa.nome,
	pessoa.cpf,
	pesEndereco.*,
	movimento_estoque.quantidade as movQuant,
	movimento_estoque.tipo_movimento,
	movimento_estoque.tipo_transacao,
	movimento_estoque.data_movimento,
	uniTransfer.unidade,
	uniTransfer.nome,
	uniTransfer.centro,
	uniTransferEndereco.*
	
FROM
	movimento_estoque
	INNER JOIN cadastro_unidade as uni ON movimento_estoque.unidade = uni.unidade
	INNER JOIN endereco as uniEndereco ON uni.endereco = uniEndereco.endereco
	INNER JOIN lote ON movimento_estoque.lote = lote.lote
	INNER JOIN cadastro_pessoa as pessoa ON movimento_estoque.pessoa = pessoa.pessoa
	INNER JOIN endereco as pesEndereco ON pessoa.endereco = pesEndereco.endereco
	INNER JOIN cadastro_unidade as uniTransfer ON movimento_estoque.unidade_transfer = uniTransfer.unidade
	INNER JOIN endereco as uniTransferEndereco ON uniTransfer.endereco = uniTransferEndereco.endereco
WHERE
	movimento = 1