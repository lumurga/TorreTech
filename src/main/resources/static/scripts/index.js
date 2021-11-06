window.addEventListener('load', (e) => {
    let
        
        formGo = document.querySelector("#userName"),
        user = document.querySelector("#data"),
        inputName = document.querySelector("#sInput"),
        skillM = document.querySelector("#skillM"),
        skillP = document.querySelector("#skillP"),
        skillN = document.querySelector("#skillN");
      

   
    formGo.addEventListener('submit', (e) => {
        e.preventDefault();
        user.innerHTML = "";
       

        fetch(`http://localhost:8080/users/${inputName.value}`)
        .then((resp) => resp.json())
        .then((info) => {
        console.log(info);
            user.innerHTML = `
                                  <div class="userPhoto">
                                    <img src="${info.urlImage}" alt="user´s photography">
                                    <h2>${info.name}</h2>
                                  </div>
                        
                                     `
            formGo.reset();
            

        }) 
        .catch((error) => console.log(error));
   })
})





// skills.innerHTML = `
// <ul class="skill" id="skills">
//       <li>${info.skills}</li>     
// </ul>



//  `
