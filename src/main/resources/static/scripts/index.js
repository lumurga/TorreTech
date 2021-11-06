window.addEventListener('load', (e) => {
    let
        btnGo = document.querySelector("#search"),
        skills = document.querySelector("#resultado"),
        user = document.querySelector(".data");
        inputName = document.querySelector("#sInput")

   
    btnGo.addEventListener('click', (e) => {
        e.preventDefault();
        user.innerHTML = "";
        skills.innerHTML = "";
        fetch(`http://localhost:8080/users/${inputName.value}`)
        .then((resp) => resp.json())
        .then((info) => {
            user.innerHTML = `
                                  <div class="userPhoto">
                                    <img src="${info.urlImage}" alt="user´s photography">
                                    <h2>${info.name}</h2>
                                  </div>
                        
                                     `
            inputName.reset();
           

        }

    )

})


skills.innerHTML = `
<ul class="skill" id="skills">
      <li>${info.skills}</li>     
</ul>



 `

})