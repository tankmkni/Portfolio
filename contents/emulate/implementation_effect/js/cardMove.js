/*****************
 *  cardMove.js  *
 *****************/

const element = document.getElementById("border")
const { x, y, width, height } = element.getBoundingClientRect()
const centerPoint = { x: x + width / 2, y: y + height / 2 }

window.addEventListener("mousemove", e => {

    const degX = (e.clientY - centerPoint.y) * 0.03
    const degY = (e.clientX - centerPoint.x) * -0.03

    element.style.transform = `perspective(1000px) rotateX(${degX}deg) rotateY(${degY}deg)`
})