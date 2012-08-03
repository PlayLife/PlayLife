var editor = null;

$('document').ready(function(){

	// inits
	editArea.init();
	controlPanel.init();

	/*$('#div_writer').bind('dblclick', function(event){
		editor = $('#div_writer').ckeditor(function () {
				var xoffset = parseInt($('#div_writer').css('left'))-10;
				var yoffset = parseInt($('#div_writer').css('top'))-38;
				$('#cke_div_writer iframe').css('display', 'none').css('display', 'block');
				$('#cke_div_writer').css({'position':'absolute','left':xoffset+'px','top':yoffset+'px'});
			}, {
			height:$('#div_writer').height(),
			width:$('#div_writer').width()+20,
			resize_minHeight:50,
			resize_minWidth:50,
			toolbar:[]
		});
		
		event.stopPropagation();
	});
	
	$('window').bind('dblclick', function(){
		console.log(editor);
	});
	
	$('body').bind('dblclick', function() {
		if(editor){
			editor.ckeditorGet().destroy();
		}
	});*/
});

// constant
var OBJECT_PREFIX = {
	TEXT: 'text_object_',
	PHOTO: 'photo_object_'
};

var SIZE_SETTINGS = {
	CANVAS_WIDTH: 500,
	CANVAS_HEIGHT: 700,
	TEXT_MAX_WIDTH: 480,
	TEXT_MAX_HEIGHT: 680,
	TEXT_MIN_WIDTH: 150,
	TEXT_MIN_HEIGHT: 150
};

// main control interface
var editArea = {
	init: function() {
		var canvas = $('<div>').attr('id', 'canvas');
		var content = $('<div>').attr('id', 'content');
		var scrollBar = $('<div>').attr('id', 'scrollBar');
		
		content.appendTo(canvas);
		scrollBar.appendTo(canvas);
		canvas.appendTo($('#div_writer'));
		
		content.droppable({
			accept: '.createBtn',
			tolerance: 'pointer',
			drop: function(event, ui) {
						//new editText(event.clientX, event.clientY);
						new editText(ui.position.left, ui.position.top);
						console.log(ui);
					}
		});
		
		// enable only in select mode
		content.selectable({
			cancel: '.object, .cke_editor_editor1'
			});
		
		$('body').bind('dblclick', function(e) {e.stopPropagation(); editArea.cancelEdit(); });
	},
	cancelEdit: function(){
		console.log('cancel');
		if($('.currentEditing').length > 0){
			var editing_elem = $('.currentEditing');
			var editing_editor = $('.currentEditing').ckeditorGet();
			
			editing_elem.html(editing_editor.getData());
			editing_editor.destroy();
			editing_elem.removeClass('ui-selected');
			editing_elem.removeClass('currentEditing');
		}
	}
};

var controlPanel = {
	init: function() {
		var panel = $('<div>').attr('id', 'control');
		var photoBtn = $('<div>').attr({
			id:'photoBtn',
			class:'createBtn'
			}).html('drag to create a photo');
		var textBtn = $('<div>').attr({
			id:'textBtn',
			class:'createBtn'
			}).html('drag to create a text box');
		
		photoBtn.appendTo(panel);
		textBtn.appendTo(panel);
		panel.appendTo($('#div_writer'));
		
		photoBtn.draggable({
			helper: 'clone',
			cursorAt: { top: 0, left: 0 },
			opacity: 0.7
		});
		photoBtn.bind('click', function() {console.log('new photo');});
		
		textBtn.draggable({
			helper: 'clone',
			cursorAt: { top: 0, left: 0 },
			opacity: 0.7
		});
		textBtn.bind('click', function() {console.log('new txt');});
	}
};

var ObjectMgr = {
	_id:0,
	_count:0,
	_objList:new Array(),
	createText: function() {
		new editText();
		//console.log(_objList);
		//console.log(this._objList.push(1));
		//console.log(ObjectMgr.a++);
	},
	createPhoto: function() {
		
	},
	getObjectID: function() {
		return 0;
	},
	findObject: function (id) {
		return null;
	}
};

// modification classes
function enableModify(selectedObj){
	$(selectedObj).addClass('ui-selected');
}

function enableContentModify(selectedTxtObj){
	var div_offset = $(selectedTxtObj).offset();
	//console.log(div_offset);
	$(selectedTxtObj).removeClass('selectedObject');
	
	$(selectedTxtObj).ckeditor(
		function() { 
			var editor = $(selectedTxtObj).ckeditorGet();
			var editor_elem = $('.'+editor.id);
			var editable_height = $(selectedTxtObj).height();
			var editable_width = $(selectedTxtObj).width();
			var editor_style = 'position:absolute;';
			
			editor_elem.attr({style:editor_style});
			
			editor.resize(SIZE_SETTINGS.TEXT_MIN_WIDTH, SIZE_SETTINGS.TEXT_MIN_HEIGHT);
			editor_elem.find('.cke_contents').attr({style:'height:'+editable_height+'px; width:'+editable_width+'px;'});
			//console.log(editor_elem.find('.cke_contents').offset());
			var editable_offset = editor_elem.find('.cke_contents').offset();
			var canvas_offset = $('#div_writer').offset();
			
			editor_elem.offset({left:div_offset.left-(editable_offset.left-canvas_offset.left) ,top:div_offset.top-(editable_offset.top-canvas_offset.top)});
			//console.log(editor_elem.find('.cke_contents').offset());
			
			$(selectedTxtObj).addClass('currentEditing');
		},
		{
		//customConfig : '../public_libs/ckeditor/config.js'
		toolbar:
		[
			[ 'Bold','Italic','Underline','Strike', 'FontSize' ]
		],
		resize_maxHeight:SIZE_SETTINGS.TEXT_MAX_HEIGHT,
		resize_maxWidth:SIZE_SETTINGS.TEXT_MAX_WIDTH,
		resize_minHeight:SIZE_SETTINGS.TEXT_MIN_HEIGHT,
		resize_minWidth:SIZE_SETTINGS.TEXT_MIN_WIDTH
		//height:$(selectedTxtObj).height(),
		//width:$(selectedTxtObj).width(),
	});
	
	
}

// presentation objects classes
function editText(x, y){
	// control obj
	
	// present obj
	var body = $('<div>').attr({
		style:'left:'+x+'px;top:'+y+'px;',
		class:'object editTxtObj'
		}).html('click to edit the text');
	
	// put in canvas
	body.appendTo($('#content'));
	
	// bind events
	body.bind('click', function(e) { e.stopPropagation(); enableModify(this); });
	body.bind('dblclick', function(e) { e.stopPropagation(); enableContentModify(this); });
	
	// attribute for object
	this.x = x;
	this.y = y;
	this.htmlElement = body.get(0);
	this.element = body;
	this.remove = function() { console.log(this); }
	
	//body.bind('dblclick', this.remove);
}

function editPhoto(x, y, url){
	var body = $('<div>');
	var imgBody = $('<img>').attr({src:url});
}