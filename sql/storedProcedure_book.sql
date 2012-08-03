DELIMITER $$

-- -----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `playlife`.`book_updateDeletedByBookSetId` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `book_updateDeletedByBookSetId`(
  IN bookSetId bigint(20)
)
BEGIN
UPDATE book b SET b.status = 'Deleted' where b.FK_BOOKSETID = bookSetId;
END $$

-- -----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `playlife`.`sheet_updateDeletedByBookId` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sheet_updateDeletedByBookId`(
  IN bookId bigint(20)
)
BEGIN
UPDATE sheet s SET s.status = 'Deleted' where s.FK_BOOKID = bookId;
END $$

-- -----------------------------------------------------------------------------

DELIMITER ;