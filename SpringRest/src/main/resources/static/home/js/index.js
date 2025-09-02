const ajax_list = document.getElementById('ajax_list');
const ajax_req = document.getElementById('ajax_req');


ajax_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();

	// 데이터를 받았을 때 발생하는 이벤트 readystatechange에 대한 처리를 미리 작성
	xhttp.addEventListener('readystatechange', (e) => {
		console.dir(e);
		console.log(xhttp.readyState);
		
		if(xhttp.readyState === 1) {
			console.log('open()에 성공했습니다.');		
		} else if (xhttp.readyState ===2) {
			console.log('xhttp 요청을 보내고 응답을 받았습니다.');
		} else if (xhttp.readyState ===3) {
			console.log('받은 응답에 대한 처리를 시작했습니다.');
		} else if (xhttp.readyState ===4) {
			console.log('받은 응답에 대한 처리가 모두 끝났습니다.');
			
			console.dir(xhttp);
			console.log('받은 상태 코드: ', xhttp.status);
			console.log('받은 데이터: ', xhttp.responseText);
			
			// 받은 데이터로 하고 싶은 일을 하면 된다!
			const newListItem = document.createElement('li');
			const newText = document.createTextNode(xhttp.responseText);
			
			newListItem.appendChild(newText);
			ajax_list.appendChild(newListItem);
		}			
	});
		
	xhttp.open("GET", "/ajax/value1"); // open()을 통해 요청 정보를 설정하고
	xhttp.send(); // send()를 통해 open()에 설정 해놓은대로 요청을 보낸다.
});


const ajax_json_list = document.getElementById('ajax_json_list');
const ajax_json_req = document.getElementById('ajax_json_req');

ajax_json_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	
	// xhttp 응답에 대한 모든 처리가 끝났을 때 발생하는 이벤트
	// (즉, readyState가 4가 되었을 때 발생하는 이벤트)
	xhttp.addEventListener('load', (e) => {
		console.log('status:', xhttp.status);
		console.log('responseText:', xhttp.responseText);
	
		// json 형식의 문자열은 자바스크립트에서 마음껏 활용할 수 있다.
		const animal = JSON.parse(xhttp.responseText);
		
		ajax_json_list.appendChild(createAnimalListItem(animal));
	});
	
	xhttp.open('GET','/ajax/value2');
	xhttp.send();
});

function createAnimalListItem(animal) {
	const animalListItem = document.createElement('li');
	animalListItem.innerText = `이름: ${animal.name}, 나이 ${animal.age}, 종류:${animal.kind}`;
	
	return animalListItem; 
}

const ajax_get_req = document.getElementById('ajax_get_req');
const ajax_post_req = document.getElementById('ajax_post_req');
const ajax_post_req2 = document.getElementById('ajax_post_req2');
const ajax_put_req = document.getElementById('ajax_put_req');
const ajax_delete_req = document.getElementById('ajax_delete_req');

ajax_get_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	xhttp.open('GET', '/ajax/req1?board_id=11&query=사과맛있다');
	xhttp.send();
});

ajax_post_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	
	xhttp.open('POST', '/ajax/req2');
	// body에 실려있는 데이터가 무슨 데이터인지 서버에 설명해줘야 한다.
	xhttp.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
	xhttp.send('board_id=12&employee_id=133');
});

ajax_post_req2.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();

	var cow = {
		name: '소',
		age: 2,
		kind: '한우' 
	};

	var jsonStr = JSON.stringify(cow);
			
	xhttp.open('POST', '/ajax/req3');
	
	// content-type을 application/json으로 설정하고 
	// 요청의 body 부분에 JSON 형식 문자열을 실어 보내면 jackson-databind가
	// 해당 JSON 형식 문자열을 객체로 바인딩하여 컨트롤러에 주입해준다.
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send(jsonStr);
});

ajax_put_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	
	var board = {
		board_password: '수정할 비밀번호',
		board_title: '수정할 글 제목',
		board_writer: '작성하신분',
		board_content: '수정할 내용'
	};
	
	var jsonStr = JSON.stringify(board);
	
	xhttp.open('PUT', '/ajax/board/3');
	xhttp.setRequestHeader('content-type', 'application/json');
	xhttp.send(jsonStr);
});

ajax_delete_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	
	xhttp.open('DELETE', '/ajax/board/5');	
	xhttp.send();	
});

const ajax_status_req = document.getElementById('ajax_status_req');
const bad = document.getElementById('bad');
const ok = document.getElementById('ok');
const not = document.getElementById('not');

ajax_status_req.addEventListener('click', (e) => {
	const xhttp = new XMLHttpRequest();
	
	xhttp.addEventListener('load', (e) => {
		// Ajax 응답에 실려있는 status를 활용해 다양한 처리를 진행할 수 있다.
		switch (xhttp.status) {
			case 200:
				console.log('200이네요');
				ok.classList.add('show');
				bad.classList.remove('show');
				not.classList.remove('show');
				break;
			case 404:
				console.log('404네요');
				ok.classList.remove('show');
				bad.classList.remove('show');
				not.classList.add('show');
				break;
	 		case 500:
				console.log('500이네요');
				ok.classList.remove('show');
				bad.classList.add('show');
				not.classList.remove('show');
				break;
		}
	});
	
	xhttp.open('PATCH', '/ajax/status');
	xhttp.send();
});
