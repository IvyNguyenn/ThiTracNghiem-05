
var counter = 0;

$( document ).ready(function() {
	let timeleft = document.getElementById("timeLimit").value * 60; // timeleft có đơn vị giây
	console.log(timeleft);
	setup(timeleft);
});

function convertSeconds(s){
	let hour = document.getElementById("hour");
	let minute = document.getElementById("minute");
	let second = document.getElementById("second");
	
	if(s<=10){
		hour.style.backgroundColor = "red"; 
		minute.style.backgroundColor = "red";
		second.style.backgroundColor = "red";
	}
	
	var hou = Math.floor(s / 3600);
	if(s>=3600) s = s-3600;
	var min = Math.floor(s / 60);
	var sec = s % 60;
	if(hou<10) hou = "0" + hou;
	if(min<10) min = "0" + min;
	if(sec<10) sec = "0" + sec;
	hour.innerHTML = hou;
	minute.innerHTML = min;
	second.innerHTML = sec;
	return hou + ":" + min + ":" + sec;
}
// Update the count down every 1 second
function setup(timeleft){
	convertSeconds(timeleft - counter);
	var interval = setInterval(timeIt,1000);
	function timeIt(){
		counter++;
		convertSeconds(timeleft - counter);
		if(counter==timeleft){
			clearInterval(interval);
			counter=0;
			window.location.assign("http://localhost:8080/ThiTracNghiem/exam-completed.jsp")
		}
	}
}