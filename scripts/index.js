
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
        
        let name = inputName.value.split(" ", 1);  
        console.log(name);
        fetch(`https://bio.torre.co/api/bios/${name}`)
        .then((resp) => resp.json())
        
        .then((data) => {
            
            //Person data is renderized
            user.innerHTML = `<div class="userPhoto">
                                 <img src="${data.person.picture}" alt="user´s photography">
                                 <h2>${data.person.name}</h2>
                                 <h4>${data.person.professionalHeadline}</h4>
                                 <h4>${data.person.location.country}</h4>
                               </div>`            
                   
            formGo.reset();
            
            //Skills are renderized
            let skills = data.person.strengths;
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
            let languages = data.person.languages;
            languages.map((language) => {
              lang.innerHTML += `
                                <li>${language.language} (${language.code}). Fluency: ${language.fluency}</li>
                                
                                   `
            })  
            
            //Industries and interests are renderized
            let interests = data.person.interests;
            interests.map((interest) => {
              ind.innerHTML += `
                               <li>${interest.name}</li>
                                `
            })


        }) 
        .catch((error) => console.log(error));
   })  
            
})



