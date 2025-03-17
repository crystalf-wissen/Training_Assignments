import axios from 'axios';
import { useEffect, useState } from 'react'
function GetEmployees() {

    const [employees, setEmployees] = useState([]);
    useEffect(()=>{
        axios.get("http://localhost:4000/employees")
        .then((resp)=>{
            //console.log(resp);
            setEmployees(resp.data);
        })
    })

    return (
        <div>
            <h3>Employee Details</h3>
            <table>
                <thead>
                    <tr>
                        <th>Emp ID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Salary</th>
                        <th>Designation</th>
                    </tr>
                </thead>
                <tbody>
                    {   employees.map((employee)=>{
                            return(
                                <tr>
                                    <td>{employee.id}</td>
                                    <td>{employee.name}</td>
                                    <td>{employee.age}</td>
                                    <td>{employee.salary}</td>
                                    <td>{employee.designation}</td>
                                </tr>
                            )
                        })}
                </tbody>
            </table>
        </div>
    )
}

export default GetEmployees;