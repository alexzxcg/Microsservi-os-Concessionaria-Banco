ALTER TABLE veiculos
ADD COLUMN categoria ENUM(
    'CARRO_DE_PASSEIO',
    'SPORT',
    'ELETRICO',
    'TRUCK',
    'SUPER_TRUCK',
    'BAIXA_CILINDRADA',
    'MEDIA_CILINDRADA',
    'ALTA_CILINDRADA'
) NOT NULL;