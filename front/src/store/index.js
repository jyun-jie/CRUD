import { createStore } from "vuex";

export default createStore({
  state: {

  },
  getters :{
    GET_TOKEN:state =>{
      return sessionStorage.getItem("token")
    }
  },
  mutations: {
   SET_TOKEN:(state,token)=>{
      sessionStorage.setItem("token",token)
   },
   DET_TOKEN:state=>{
    sessionStorage.removeItem("token")
   }
  },
  actions: {
     
  },modules:{

  }
  }
)