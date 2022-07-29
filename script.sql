USE PAPETERIE_DB
GO
CREATE TABLE dbo.Articles(
	idArticle int IDENTITY(1,1) NOT NULL,
	reference nchar(10) NOT NULL,
	marque nvarchar(200) NOT NULL,
	designation nvarchar(250) NOT NULL,
	prixUnitaire float NOT NULL,
	qteStock int NOT NULL,
	grammage int NULL,
	couleur nvarchar(50) NULL,
	type nchar(10) NOT NULL,
 CONSTRAINT PK_Articles PRIMARY KEY  
(
	idArticle
))
GO


USE PAPETERIE_DB
GO
ALTER TABLE articles
ADD CONSTRAINT un_designation_articles UNIQUE(designation)