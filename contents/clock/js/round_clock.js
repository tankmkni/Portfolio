var id;
var canvas;
var c;

var center = 100;
var milli = getRadian(0.003);
var reverse = getRadian(180);

var longColor   = '#000';
var shortColor  = '#333';
var secondColor = '#666';
var centerColor = 'orange';

var handColor = 'royalblue';

var hourHandWidth = 7;
var hourHandLength = 60;

var minHandWidth = 4;
var minHandLength = 75;

var secHandWidth = 3;
var secHandLength = 75;
var secHandTailLength = 20;

var centralAxisSize = 10;

var hour;
var min;
var sec;
var mil;

var tmpSec = 0;
var secTenth = 0;
var degTenth = 0;
var degHundredth = 0;

//秒針の色のパターン
var secondColorPattarn = 0;

//	ランダムで色を変える間隔（秒）
var interval = 4;

//	秒針の色のパターンを決める変数
var secondColorPattarn = 0;

//	七色の配列
var rainbow = ['red','orange','yellow','lime','cyan','blue','blueviolet'];

//	七色の配列+α
var rainbowPlus = ['red','orange','yellow','lime','cyan','blue','blueviolet'
	,'magenta'
	,'ivory'
	,'chocolate'
	,'green'
	,'crimson'
	,'greenyellow'
	,'dodgerblue'
	,'skyblue'
	];

//	オリンピックマークの色
var olympicSymbole = ['cyan','yellow','black','lime','red']

//	マークの動きを決める変数( true = 機械式風、false = クォーツ風 )
var mechanicalMotion = false;

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

//コスモクロック風------------------------------------------------------------------------------------------------------
function drawCosumoClockLike(){

	id = 'cosumoClock';
	canvas = document.getElementById( id );
	c = canvas.getContext('2d');

	secHandWidth = 2;
	secHandLength = 100;
	secHandTailLength = 0;

//	時間の情報を取得
	getNow();

//	1秒に１回だけ描画
	if( sec != tmpSec ){
		tmpSec = sec;

//		一分毎にcanvasの描画を消去
		if(sec == 0){
			c.clearRect( 0, 0, 300, 300 );
			//一分毎に、秒針の色を変える間隔を3～6秒の間でランダムに変更
			interval = Math.floor( Math.random() * 4 ) + 3;
		}

//		指定した秒毎に秒針の色を変える
		if( sec % interval == 0){
			changeSecondColor();
		}

		getDigitalClock();	//	デジタル時計の取得
		roundSecondHand();	//	秒針
	}
}

//秒針の色を変える------------------------------------------------------------------------------------------------------
function changeSecondColor(){
	switch( secondColorPattarn ){
	case 0:
		secondColor = randomColor( 16 );
		return;
	case 1:
		secondColor = rainbow[ Math.floor( Math.random() * rainbow.length ) ];
		return;
	case 2:
		secondColor = rainbowPlus[ Math.floor( Math.random() * rainbowPlus.length ) ];
		return;
	case 3:
		secondColor = olympicSymbole[ Math.floor( Math.random() * olympicSymbole.length ) ];
	}
}

//秒針のカラーパターンを切り替える--------------------------------------------------------------------------------------
function changeSecondColorPattarn(){
	secondColorPattarn++;
	if( secondColorPattarn > 3 ){
		secondColorPattarn = 0;
	}
}

//どこつぶマークの時計--------------------------------------------------------------------------------------------------
function drawDocoTsubuClock(){

	id = 'docoTsubuClock';
	canvas = document.getElementById( id );
	c = canvas.getContext('2d');

//	canvasの描画を消去
	c.clearRect( 0, 0, 300, 300 );

//	時間の情報を取得
	getNow();

//	マークを1秒毎に回転
	roundMark( document.getElementById('secondMark') );

	longColor   = handColor;
	shortColor  = longColor;

	hourHandWidth = 24.27;
	hourHandLength = 50;

	minHandWidth = 15;
	minHandLength = 80.9;

	roundHourHand();	//	短針
	roundMinuteHand();	//	長針
	centralAxis();		//	中心軸
}

//マークを回転---------------------------------------------------------------------------------------------------------
function roundMark( img ){

	deg = sec * 6;

	if( mechanicalMotion ){
		if( sec == tmpSec ){
			if( secTenth == 9 ){//値によってカクカクな動き（処理速度？）(5～10)
				degTenth += 0.6;
				secTenth = 0;
				degHundredth = 0;
			}else{
				secTenth++;
				degHundredth += 0.06;
			}
		}else{
			tmpSec = sec;
			secTenth = 0;
			degTenth = 0;
			degHundredth = 0;
		}
		
	deg += degTenth + degHundredth;
	}

	img.style.transform = 'rotateZ(' + deg + 'deg)';
}

//マークの動きをクォーツ風と機械式風で切り替える----------------------------------------------------------------------
function switchMotion(){
	switch( mechanicalMotion ){
	case true:
		mechanicalMotion = false;
		return;
	case false:
		mechanicalMotion = true;
	}
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

//デジタル時計------------------------------------------------------------------------------------------------------
function getDigitalClock(){
	var formatMin = min;
	if( min < 10 ){
		formatMin = "0" + min;
	}

	var nowTime = hour + " : " + formatMin;
	document.getElementById("digitalClock").innerHTML = nowTime;
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

//【色指定番号(６桁の16進数)を生成】------------------------------------------------------------------------------------
function randomColor( hexa ){								//hexa = 16を受け取る
	var code = "#";											//指定コードの頭文字
	for( i = 0 ; i < 6 ; i++){								//６桁の16進数を取得するループ
		var rnd = Math.floor( Math.random() * hexa );		//ランダム値（0～15）を取得
		code += rnd.toString( 16 );							//10進数のランダム値を16進数にして合体
	}
	return code;											//指定コードを返す
}

//【時計の針の色をランダムで変更】--------------------------------------------------------------------------------------
function randomClockHandColor(){
	handColor = randomColor( 16 );
	centerColor = randomColor( 16 );
}

