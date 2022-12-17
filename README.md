# swing-java-certificate-generator
Gerador de certificados PDF desktop.

Versão desktop gerador de certificados de conclusão.

Aplicativo desktop para conversão de informções passadas via XML para um rlatório PDF.

Método utilizado para recebimento do arquivo XML? Foi utilizado o FrameWork APACHE POI, possibilitando o recebimento e trabalho com arquivos XMLX. Através do método Create, a API recebe uma tabela XMLX e gera um modelo de certificado, que é adicionado a uma lista de certificados. O método Create retorna essa lista.

Método utilizado para criação dos PDFs? foi utilizado o editor JasperSoft, que além de disponibilizar alguns temas pré-prontos, possibilita criação e personalização de temas próprios, podendo gerar um certificado com logomarcas, etc. O método generatePdf recebe a lista de certificados criada anteriormente e acessa cada um dos certificados da lista, gerando um documento PDF para cada

OBS: O arquivo XMLX para teste está na pasta resources. 

Obrigado pela atenção!
