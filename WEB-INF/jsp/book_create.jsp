<link rel="stylesheet" type="text/css" href="css/book_create.css">

<script type="text/javascript" src="script/date.js"></script>
<script type="text/javascript" src="script/book_create.js"></script>

Write <br /><br />
<div id="div_register">
	<form action="book.do?action=create" method="post" id="fm_create">
        Name : <input type="text" id="tb_name" name="bookname"/><br />
        Date : From <input type="text" id="tb_startDate" name="startDate" class="date"/> to <input type="text" id="tb_endDate" name="endDate" class="date"/><br />
        Budget : <input type="text" id="tb_budget" name="budget"/><br /><br/>
        <input type="submit" id="bn_submit" name="submit"/>
    </form>
</div>