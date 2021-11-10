


window.addEventListener('load', (e) => {
  let
  qs = element => document.querySelector(element),
  formGo = qs("#userName"),
  user = qs("#data"),
  inputName = qs("#sInput"),
  skillM = qs("#skillM"),
  skillP = qs("#skillP"),
  skillNov = qs("#skillNov"),
  skillN = qs("#skillN"),
  skillE = qs("#skillE"),
  lang = qs("#lang"),
  ind = qs("#ind");
  
  
   
    formGo.addEventListener('submit', (e) => {
        e.preventDefault(); 
        
        let userName = inputName.value.split(" ", 1);  
        let proxy = "https://cors-everywhere-me.herokuapp.com/";
        let url =  proxy + `https://bio.torre.co/api/bios/${userName}`;

        fetch(url)
        .then(response => {
        if(response.status != 200){
        console.log(`Error: ${response.status}`)
        }else{
           return response.json()
         }
      })
    
        .then((data) => {
            
            //Person data is renderized
            user.innerHTML = `<div class="userPhoto">
                                 <img src="${data.person.picture==undefined?"https://image.freepik.com/free-vector/404-error-page-found_24908-59519.jpg":data.person.picture}" alt="user´s photography">
                                 <h2>${data.person.name}</h2>
                                 <h4>${data.person.professionalHeadline}</h4>
                                 <h4>${data.person.location.country}</h4>
                               </div>`            
                   
            formGo.reset();
            
            //Skills are renderized
            let skills = data.strengths;
            skills.map((skill) => {


              if(skill.proficiency==="proficient"){
                
                return skillP.innerHTML+=`
                                  <li>${skill.name}</li>   
                                  `
              }
              else if(skill.proficiency==="novice"){
                return skillNov.innerHTML+=`
                                  <li>${skill.name}</li>   
                                  `
              }
              else if(skill.proficiency==="no-experience-interested"){
                return skillN.innerHTML+=`
                                  <li>${skill.name}</li>   
                                  `
              }
              else if(skill.proficiency==="master-influencer"){
                return skillM.innerHTML+=`
                                  <li>${skill.name}</li>   
                                  `
              }
              else if(skill.proficiency==="master-influencer"){
                return skillM.innerHTML+=`
                                  <li>${skill.name}</li>   
                                  `
              }
              else if(skill.proficiency==="expert"){
                return skillE.innerHTML+=`
                                  <li>${skill.name}</li>   
                                  `
              }
              
            })

            //Languages are renderized          
            let languages = data.languages;
            languages.map((language) => {
              lang.innerHTML += `
                                <li>${language.language} (${language.code})</li>
                                
                                   `
            })  
            
            //Industries and interests are renderized
            let interests = data.interests;
            interests.map((interest) => {
              ind.innerHTML += `
                               <li>${interest.name}</li>
                                `
            })


        }) 
        .catch((error) => console.log(error));
   })  
            
})



