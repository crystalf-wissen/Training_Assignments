import React, { useState } from 'react'
import axios from 'axios'
function EmployeeOperations() {
    const[emp, setEmp] = useState({"id":"", "name":"", "age":"", "salary":"", "designation":""})

    const assignData = (e)=>{
        setEmp({
            ...emp,[e.target.name]:e.target.value
        })
    }

    const insertEmp = (e)=> {
        e.preventDefault();
        axios.post("http://localhost:4000/employees",emp)
        .then((resp)=>{
            console.log("Employee added successfully...")
        })
    }

    const updateEmp = (e)=> {
        e.preventDefault();
        axios.put("http://localhost:4000/employees/"+emp.id,emp)
        .then((resp)=>{
            console.log("Employee updated successfully...")
        })
    }

    const deleteEmp = (e)=> {
        e.preventDefault();
        axios.delete("http://localhost:4000/employees/"+emp.id)
        .then((resp)=>{
            console.log("Employee updated successfully...")
        })
    }

  return (
    <div>
    <h3>Please Enter Employee Details</h3>
    <input type='text' value={emp.id} name='id' onChange={assignData} placeholder='Enter Employee ID' required/><br/>
    <input type='text' value={emp.name} name='name' onChange={assignData} placeholder='Enter Employee Name' required/><br/>
    <input type='number' value={emp.age} name='age' onChange={assignData} placeholder='Enter Employee Age' required/><br/>
    <input type='number' value={emp.salary} name='salary' onChange={assignData} placeholder='Enter Employee Salary' required/><br/>
    <input type='text' value={emp.designation } name='designation' onChange={assignData} placeholder='Enter Employee Designation' required/><br/>
    <button type='submit' onClick={insertEmp}>Add Employee</button>
    <button type='button' onClick={updateEmp}>Update Employee</button>
    <button type='button' onClick={deleteEmp}>Delete Employee</button>
    </div>
  )
}

export default EmployeeOperations
