const modi_btns = document.querySelectorAll(".modi_btn");
const del_btns = document.querySelectorAll(".del_btn");

modi_btns.forEach(modi_btn => {
	modi_btn.addEventListener('click', modifyEvenet)});

del_btns.forEach(del_btn => {
	del_btn.addEventListener('click', deleteEvenet)});

function modifyEvenet(e) {
	console.log('수정 버튼 클릭함!', e.target);
	
	location.href =`./modify?book_id=${e.target.dataset.id}`;
}

function deleteEvenet(e) {
	console.log('삭제 버튼 클릭함!', e.target);
	
	// 해당 태그의 data-* 어트리뷰트에 원하는 값을 보관해 놓으면 dataset으로 접근하여 꺼낼 수 있다.
	console.log('삭제 해야하는 상품의 ID: ', e.target.dataset.id);
	
	// 자바스크립트에서 요청보내는 방법
	
	// 1. Query String (GET 방식 요청)
	// location.href = `./delete?book_id=${e.target.dataset.id}
	
	// 2. form 사용하기 
	e.target.nextElementSibling.submit();
	
}