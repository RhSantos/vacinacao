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
	movimento_estoque.quantidade as movQuant,
	movimento_estoque.tipo_movimento,
	movimento_estoque.tipo_transacao,
	movimento_estoque.data_movimento,
	uniTransfer.unidade AS uniTransfer,
	uniTransfer.nome AS uniTransferNome,
	uniTransfer.centro AS uniTransferCentro,
	uniTransferEndereco.endereco AS uniTransferEndereco,
	uniTransferEndereco.logradouro AS uniTransferLogradouro,
	uniTransferEndereco.cidade AS uniTransferCidade,
	uniTransferEndereco.estado AS uniTransferEstado,
	uniTransferEndereco.numero AS uniTransferNumero,
	uniTransferEndereco.bairro AS uniTransferBairro,
	uniTransferEndereco.complemento AS uniTransferComplemento,
	uniTransferEndereco.cep AS uniTransferCep
FROM
	movimento_estoque
	INNER JOIN cadastro_unidade AS uni ON movimento_estoque.unidade = uni.unidade
	INNER JOIN endereco AS uniEndereco ON uni.endereco = uniEndereco.endereco
	INNER JOIN lote ON movimento_estoque.lote = lote.lote
	INNER JOIN cadastro_unidade AS uniTransfer ON movimento_estoque.unidade_transfer = uniTransfer.unidade
	INNER JOIN endereco as uniTransferEndereco ON uniTransfer.endereco = uniTransferEndereco.endereco
WHERE movimento = 2