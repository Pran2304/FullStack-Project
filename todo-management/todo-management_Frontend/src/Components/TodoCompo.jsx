import React, { useEffect } from 'react'
import {useState} from 'react'
import {getTodo, saveTodo, updateTodo} from '../Services/TodoService'
import { useNavigate,useParams } from 'react-router-dom'


export default function TodoCompo() {
const[tittle,setTittle]=useState('')
const[description,setDescription]=useState('')
const[completed,setCompleted]=useState('')

const {id}=useParams()
const navigate=useNavigate()

function saveOrUpdateTodo(e){
    e.preventDefault()

    const todo ={tittle,description,completed}
    console.log(todo);

    if(id){
        updateTodo(id,todo).then((response)=>{
            navigate('/todos')
        }).catch(error=>{
            console.error(error);
        })
    }
    else{
      saveTodo(todo).then((response)=>{
        console.log(response.data)
        navigate('/todos')
    }).catch(error=>{
        console.error(error);
    })
}
}

function pageTittle(){
    if(id){
         return<h2 className='text-center'>Update Todo</h2>
    }else{
        return<h2 className='text-center'>Add Todo</h2>
    }
}

useEffect(()=>{
   if(id){
    getTodo(id).then((response)=>{
    console.log(response.data)
    setTittle(response.data.tittle)
    setDescription(response.data.description)
    setCompleted(response.data.completed)
}).catch(error=>{
    console.error(error)
})
   }
},[id])

  return (
    <div className='container'>
        <br></br>
        <br>
        </br>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
               {pageTittle()}
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Tittle</label>
                            <input 

                            type='text'
                            className='form-control'
                            placeholder='Enter Todo Tittle'
                            name='tittle'
                            value={tittle}
                            onChange={(e)=>setTittle(e.target.value)}>

                            </input>
                        </div>


                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Description</label>
                            <input 
                            
                            type='text'
                            className='form-control'
                            placeholder='Enter Todo Description'
                            name='description'
                            value={description}
                            onChange={(e)=>setDescription(e.target.value)}>

                            </input>
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Completed</label>
                           <select 
                           className='form-control'
                           value={completed}
                           onChange={(e)=>setCompleted(e.target.value)}>

                            <option value="false">No</option>
                            <option value="true">Yes</option>

                           </select>
                        </div>
                        <button className='btn btn-success' onClick={(e)=> saveOrUpdateTodo(e)}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
     
    </div>
  )
}
