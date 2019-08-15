var Article__ajaxEffectDelay = 500;

function Article__doAddReply(form) {
	form.body.value = form.body.value.trim();
	
	if ( form.body.value.length == 0 ) {
		form.body.focus();
		return;
	}
	
	form['submit-btn'].value = "작성중...";
	form['submit-btn'].disabled = true;

	$.post('./doAddReply', {
		body : form.body.value,
		articleId : articleId
	}, function(data) {
		
	}, 'json');

	form.body.value = '';
	form.body.focus();
}

function Article__doDeleteReply(el){
	if ( confirm('정말 삭제하시겠습니까?') == false ) {
		return;
	}
	
	var $tr = $(el).closest('tr');
	var id = parseInt($tr.attr('data-id'));
	
	$tr.addClass('add-animation-item-now-deleting')
	$tr.find('.body-view').empty().append('삭제중...');
	
	$.post("./doDeleteReply",
		{
			id : id
		},
		function(data){
			var effectDelay = Article__ajaxEffectDelay;
			
			setTimeout(function() {
				$tr.remove();				
			}, effectDelay);
		},
		"json"
	);
}

var Article__lastReceivedReplyId = 0;

function Article__loadNewReplies() {
	
	$.get(
		'./getReplies',
		{
			articleId: articleId,
			from: Article__lastReceivedReplyId + 1
		},
		function(data) {
			for ( var i = 0; i < data.replies.length; i++ ) {
				var reply = data.replies[i];
				Article__lastReceivedReplyId = reply.id;
				Article__drawReply(reply);
			}
			
			if ( data.replies.length > 0 ) {
				var form = document['add-reply-form'];
				form['submit-btn'].value = "댓글작성";
				form['submit-btn'].disabled = false;
			}
			
			setTimeout(Article__loadNewReplies, 1000);
		}
	);
}

function Article__enableReplyEditMode(el) {
	var $tr = $(el).closest('tr');
	$tr.addClass('edit-mode');
}

function Article__doModifyReply(form) {
	form.body.value = form.body.value.trim();
	var body = form.body.value;
	
	if ( body.length == 0 ) {
		alert('댓글 내용을 입력해주세요.');
		form.body.focus();
		
		return false;
	}
	
	var $tr = $(form).closest('tr');
	var id = parseInt($tr.attr('data-id'));
	
	$.post("./doModifyReply",
		{
			id : id,
			body: body
		},
		function(data) {
			var effectDelay = Article__ajaxEffectDelay;
			
			setTimeout(function() {
				$tr.find('.body-view').text(body);
				$tr.removeClass('add-animation-item-just-modifed');
			}, effectDelay);
		},
		"json"
	);
	
	Article__disableReplyEditMode(form);
	$tr.find('.body-view').text('수정중...');
	$tr.addClass('add-animation-item-just-modifed');
}

function Article__disableReplyEditMode(el) {
	var $tr = $(el).closest('tr');
	$tr.removeClass('edit-mode');
}

function Article__drawReply(reply) {
	var 번호 = reply.id;
	var 등록날짜 = reply.regDate;
	var 댓글번호 = reply.id;
	var 회원번호 = reply.memberId;
	
	var 내용 = `
	<div>
		<div class="edit-mode-visible">
			<form onsubmit="Article__doModifyReply(this); return false;">
				<input type="hidden" name="id" value="${번호}">
				<input type="text" name="body">
				<div>
					<input type="submit" value="수정">
					<input type="reset" value="취소" onclick="Article__disableReplyEditMode(this);">
				</div>
			</form>
		</div>
		
		<div class="read-mode-visible body-view">
			${reply.body}
		</div>	
	</div>
	`;
	
	var $내용 = $(내용);
	$내용.find('[name=body]').attr('value', reply.body);
	
	var 내용 = $내용.html();
	
	var 비고 = `
	<div class="editable-item">
		<a class="read-mode-visible" href="javascript:;" onclick='Article__enableReplyEditMode(this)'>수정</a>
		<a class="btn-delete" href="javascript:;" onclick="Article__doDeleteReply(this);">삭제</a>
	</div>
	`;
	
	var editableClass = '';
	
	if ( 회원번호 == loginedMemberId ) {
		editableClass = 'editable';
	}
	
	var html = `
	<tr data-id="${번호}" data-member-id="${회원번호}" class="${editableClass} add-animation-item add-animation-item-just-added">
        <td>${댓글번호}</td>
   		<td>${등록날짜}</td>
   		<td>${내용}</td>
   		<td>${비고}</td>
   	</tr>
    `;
	
	var $html = $(html);
    
    $('.article-replies-list tbody').prepend($html);
    
    setTimeout(function() {
    	$html.removeClass('add-animation-item-just-added');
    }, 500);
};


$(function() {
	Article__loadNewReplies();
});

