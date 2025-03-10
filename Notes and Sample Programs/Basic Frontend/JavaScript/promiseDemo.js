// catch executed only when some error is thrown
let s1 = "Wissen";
let s2 = "Technlogy";

var promise = new Promise(function(resolved, rejected) {
    if((s1+s2) == "WissenTechnology") {
        setTimeout(()=>resolved=("Emp object"),5000);
    } else {
        rejected();
    }
});

promise.then(
    (val)=>{
        console.log("Best place to work");
        console.log('Got : '+val);
        return "Staff Feedback";
    },
    ()=>{
        console.log("Theres some spelling mistake");
        throw new  Error("o is missing in technology");
    }
).catch(
    (err)=>{
        console.log("Error handling done here");
        console.log('Reason : '+err);
    }
) .finally(()=>{
        console.log("This is always executed");
    });