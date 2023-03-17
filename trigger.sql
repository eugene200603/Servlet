
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
    IF (SELECT COUNT(*) FROM inserted) = 1 -- 第一次提交
   BEGIN
        UPDATE Article1
        SET CreateTime = GETDATE()
        WHERE ArticleID = (SELECT ArticleID FROM inserted)
    END
    ELSE if (SELECT COUNT(*) FROM inserted) > 1 -- 第二次以上提交
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
        CreateTime = COALESCE(CreateTime, GETDATE()), -- 如果發文時間為 NULL 則填入當前時間
        UpdateTime = CASE 
            WHEN CreateTime IS NULL THEN UpdateTime -- 如果發文時間為 NULL，最後修改時間不變
            ELSE GETDATE() -- 如果發文時間不為 NULL，最後修改時間更新為當前時間
        END
    WHERE ArticleID IN (SELECT ArticleID FROM inserted)
END


CREATE TRIGGER ArtDateTri
ON Article1
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- 處理 CreateTime
    UPDATE Article1
    SET CreateTime = COALESCE(CreateTime, GETDATE())
	
    WHERE ArticleID IN (SELECT ArticleID FROM inserted);
SET UpdateTime = COALESCE(UpdateTime, GETDATE())
WHERE ArticleID IN (SELECT ArticleID FROM inserted);
    -- 處理 UpdateTime
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

    -- 更新 CreateTime 和 UpdateTime
    UPDATE [dbo].[Article]
    SET 
        CreateTime = COALESCE(CreateTime, GETDATE()),
        UpdateTime = CASE WHEN CreateTime IS NULL THEN COALESCE(UpdateTime, GETDATE()) ELSE GETDATE() END
    WHERE ArticleID IN (SELECT ArticleID FROM inserted);

END