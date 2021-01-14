/*************
 *  test.js  *
 *************/

const elm = document.getElementById("outer")
const { x, y, width, height } = elm.getBoundingClientRect()
const centerPoint = { x: x + width / 2, y: y + height / 2 }

window.addEventListener("mousemove", e => {
  const degreeX = (e.clientY - centerPoint.y) * 0.03
  const degreeY = (e.clientX - centerPoint.x) * -0.03
 
  elm.style.transform = `perspective(1000px) rotateX(${degreeX}deg) rotateY(${degreeY}deg)`
})