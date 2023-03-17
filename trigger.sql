
DROP TRIGGER ArtDateTri ;
DROP TRIGGER ArtDateIn ;
DROP TRIGGER ArtDateUp ;

CREATE TRIGGER ArtDateIn
ON Article1
AFTER INSERT
AS
BEGIN
        UPDATE Article1
        SET CreateTime = GETDATE()
        WHERE ArticleID = (SELECT ArticleID FROM inserted)
    END
    


CREATE TRIGGER ArtDateUp
ON Article1
AFTER  UPDATE
AS
    BEGIN
        UPDATE Article1
        SET UpdateTime = GETDATE()
        WHERE ArticleID IN (SELECT ArticleID FROM inserted)
    END


	CREATE TRIGGER ArtDateTri
ON Article1
AFTER INSERT, UPDATE
AS
BEGIN
    IF (SELECT COUNT(*) FROM inserted) = 1 -- �Ĥ@������
   BEGIN
        UPDATE Article1
        SET CreateTime = GETDATE()
        WHERE ArticleID = (SELECT ArticleID FROM inserted)
    END
    ELSE if (SELECT COUNT(*) FROM inserted) > 1 -- �ĤG���H�W����
	BEGIN
        UPDATE Article1
        SET UpdateTime = GETDATE()
        WHERE ArticleID IN (SELECT ArticleID FROM inserted)
    END
END

CREATE TRIGGER ArtDateTri
ON Article1
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE Article1
    SET 
        CreateTime = COALESCE(CreateTime, GETDATE()), -- �p�G�o��ɶ��� NULL �h��J��e�ɶ�
        UpdateTime = CASE 
            WHEN CreateTime IS NULL THEN UpdateTime -- �p�G�o��ɶ��� NULL�A�̫�ק�ɶ�����
            ELSE GETDATE() -- �p�G�o��ɶ����� NULL�A�̫�ק�ɶ���s����e�ɶ�
        END
    WHERE ArticleID IN (SELECT ArticleID FROM inserted)
END


CREATE TRIGGER ArtDateTri
ON Article1
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- �B�z CreateTime
    UPDATE Article1
    SET CreateTime = COALESCE(CreateTime, GETDATE())
	
    WHERE ArticleID IN (SELECT ArticleID FROM inserted);
SET UpdateTime = COALESCE(UpdateTime, GETDATE())
WHERE ArticleID IN (SELECT ArticleID FROM inserted);
    -- �B�z UpdateTime
    UPDATE Article1
    SET UpdateTime = GETDATE()
    WHERE ArticleID IN (SELECT ArticleID FROM inserted) AND CreateTime IS NOT NULL;
END



---------------

CREATE TRIGGER ArtDateTri
ON [dbo].[Article]
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- ��s CreateTime �M UpdateTime
    UPDATE [dbo].[Article]
    SET 
        CreateTime = COALESCE(CreateTime, GETDATE()),
        UpdateTime = CASE WHEN CreateTime IS NULL THEN COALESCE(UpdateTime, GETDATE()) ELSE GETDATE() END
    WHERE ArticleID IN (SELECT ArticleID FROM inserted);

END