var id;
var canvas;
var c;

var center = 100;
var milli = getRadian(0.003);
var reverse = getRadian(180);

var longColor   = '#000';
var shortColor  = '#333';
var secondColor = 'red';
var centerColor = 'orange';

var handColor = 'royalblue';

var hourHandWidth = 35;
var hourHandLength = 60;

var minHandWidth = 25;
var minHandLength = 90;

var secHandWidth = 20;
var secHandLength = 85;
var secHandTailLength = 35;

var centralAxisSize = 20;

var hour;
var min;
var sec;
var mil;

var tmpSec = 0;
var secTenth = 0;
var degTenth = 0;
var degHundredth = 0;

//オリジナルの時計------------------------------------------------------------------------------------------------------
function drawOrigin(){

	id = 'clock';
	canvas = document.getElementById( id );
	c = canvas.getContext('2d');

//	canvasの描画を消去
	c.clearRect( 0, 0, 300, 300 );

//	時間の情報を取得
	getNow();

	roundHourHand();	//	短針
	roundMinuteHand();	//	長針
	roundSecondHand();	//	秒針
	centralAxis();		//	中心軸
}

//----------------------------------------------------------------------------------------------------------------------
function circle( size ){
	c.beginPath();
	c.arc( center, center, size, 0, Math.PI * 2, false );
	c.fill();
	c.closePath();
}

//----------------------------------------------------------------------------------------------------------------------
function line(size, angle){
	c.beginPath();
	c.moveTo( center, center );
	c.arc( center, center, size, angle, angle, false );
	c.stroke();
	c.closePath();
}

//----------------------------------------------------------------------------------------------------------------------
function getRadian(degree){
	return degree * Math.PI / 180;
}

//時間の情報を取得------------------------------------------------------------------------------------------------------
function getNow(){
	now		= new Date();
	hour 	= now.getHours();
	min		= now.getMinutes();
	sec		= now.getSeconds();
	mil		= now.getMilliseconds();
}

//短針------------------------------------------------------------------------------------------------------------------
function roundHourHand(){
	if(hour > 11){
		hour -= 12;
	}

	hour = hour * 0.523 - Math.PI/2 + (min / 12 * 0.104);
	c.strokeStyle = shortColor;
	c.lineCap = 'round';
	c.lineWidth = hourHandWidth;
	line( hourHandLength, hour );
}

//長針------------------------------------------------------------------------------------------------------------------
function roundMinuteHand(){
	min  = min  * 0.104 - Math.PI/2;
	c.strokeStyle = longColor;
	c.lineWidth = minHandWidth;
	line( minHandLength, min );
}

//秒針------------------------------------------------------------------------------------------------------------------
function roundSecondHand(){
	sec  = sec  * 0.104 - Math.PI/2;
	c.strokeStyle = secondColor;
	c.lineWidth = secHandWidth;
	line( secHandLength, sec );
//	秒針の先っちょ
	line( secHandTailLength, sec + reverse );
}

//中心軸----------------------------------------------------------------------------------------------------------------
function centralAxis(){
	c.fillStyle = centerColor;
	circle( centralAxisSize );
}
