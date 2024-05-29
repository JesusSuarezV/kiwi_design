const nav =document.querySelector('.navegacion')
const foot =document.querySelector('.footer')

fetch('../views/navbar-index.html')
.then(res=>res.text())
.then(data=>{
    nav.innerHTML=data
    const parser = new DOMParser()
    const doc = parser.parseFromString(data, 'text/html')
    eval(doc.querySelector('script').textContent)
})

fetch('../views/footer.html')
.then(res=>res.text())
.then(data=>{
    foot.innerHTML=data
    const parser = new DOMParser()
    const doc = parser.parseFromString(data, 'text/html')
    eval(doc.querySelector('script').textContent)
})


