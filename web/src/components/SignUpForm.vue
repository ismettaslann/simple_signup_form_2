<template>
    <div class="signup"> 
      <b-card img-src="../assets/logo.png" img-alt="Card image" img-left ><!--Sol tarafında resim olan card kullandım.-->
        <!--Formun action metodlarını tanımladık. 
          OnSubmit olduğunda script altındaki hangi metot çalışacak?
          Reset butonuna basıldığında hangi metot çalışacak vs.
        -->
        <b-form @submit="onSubmit" @reset="onReset"> 
          <!--
            description inputun altında gözükecek yardım yazısı,
            label-cols-lg = kolonların 12 lik düzendeki boyutu ben 3'e 7 şeklinde boyutlandırdım
            label-align="left" input'a bağlı labelların sola hizalı durması için eklendi
            label-for id bazında hangi inputa bağlı olduğunu gösterir
            v-model htmldeki form elementlerinin js tarafıyla binding edilmesini sağlar
            state validation için inputların valid olup olmadığını gösterecek (true:valid, false:invalid)
          -->
          <b-form-group
            id="input-group-1"
            label-for="input-1"
            label="Email address:"  
            description="We'll never share your email with anyone else." 
            label-cols-lg="3"
            content-cols-lg="7"
            label-align="left"
          >
            <b-form-input
              id="input-1"
              v-model="form.email"
              type="email"
              placeholder="Enter email"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group 
            id="input-group-2" 
            label="Your Name:" 
            label-for="input-2" 
            label-cols-lg="3" 
            content-cols-lg="7" 
            label-align="left">
            <b-form-input
              id="input-2"
              v-model="form.name"
              placeholder="Enter name"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group 
            id="input-group-3" 
            label="Password:" 
            label-for="input-3" 
            label-cols-lg="3" 
            content-cols-lg="7" 
            label-align="left">
            <b-form-input
              id="input-3"
              v-model="form.password"
              placeholder="Enter password"
              type="password"
              :state="passwordState"
              required
            ></b-form-input>
            <!--input altında state false olduğu durumda gözükecek form-->
            <b-form-invalid-feedback id="input-live-feedback">
              Be sure your inputs are same.
            </b-form-invalid-feedback>
          </b-form-group>

          <b-form-group 
            id="input-group-4" 
            label="Password Again:" 
            label-for="input-4" 
            label-cols-lg="3" 
            content-cols-lg="7" 
            label-align="left">
            <b-form-input
              id="input-4"
              v-model="form.passwordCheck"
              placeholder="Enter password again"
              type="password"
              :state="passwordCheckState"
              aria-describedby="input-live-feedback"
              required
            ></b-form-input>
            <b-form-invalid-feedback id="input-live-feedback">
              Be sure your inputs are same.
            </b-form-invalid-feedback>
          </b-form-group>


          <b-row>
            <b-col lg="3">
            </b-col>
            <b-col lg="7">
              <b-button type="submit" class="mr-2" variant="outline-dark">Sign Up</b-button>
              <b-button type="reset" variant="outline-info">Clear</b-button>
            </b-col>
           </b-row>
          
        </b-form>
      </b-card>
  </div>
</template>

<script>
import {post} from '../common/api-service'

export default {
    /* 
      data default olarak html kısmına dönen kısım sayfa açılışında set edilmesi gereken,
      değişkenle yönetilmesi gereken kısımlar için vs. ilk tanımlamaları data metodunun altında yapabiliriz.
    */
    data() {
      return {
        form: {
          email: '',
          name: '',
          password: '',
          passwordCheck: ''
        },
        /*
          Ekranki iki password aynı değilse bu state değişkenlerini true false şekilnde manipule edeceğiz.
          İlk başta null olarak set ediyoruz ki normal input olarak açılsın 
        */
        passwordState: null,
        passwordCheckState: null
      }
    },
    methods: {
      /* Form gönderildiğinde çalışacak metot */
      onSubmit(event) {
        event.preventDefault()
        /* 
          Formdan gelen şifreler aynı mı diye kontrol ediyoruz.
          Aynı ise popup göstereceğiz.
        */
        if(this.form.password === this.form.passwordCheck){
          post('/api/signup/', this.form).then(response => {
            if(response.data.status){
              this.$bvModal.msgBoxOk("Kayıt başarıyla eklendi!", {
                title: 'Başarılı',
                size: 'sm',
                buttonSize: 'sm',
                okVariant: 'success',
                headerClass: 'p-2 border-bottom-0',
                footerClass: 'p-2 border-top-0',
                centered: true
              })
            }
            else{console.log(response)}
          })
        }
        else{
          this.passwordState = false
          this.passwordCheckState = false
        }
        
      },
      /* Reset butonuna basıldığında çalışacak metot. Tüm değerleri ilk değerine çekiyoruz. */
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.password = ''
        this.form.passwordCheck = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.passwordState = null
        this.passwordCheckState = null
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
}
</script>

<style scoped>

.signup {
    margin-top: 10%;
    margin-left: 15%;
    margin-right: 15%;
}
</style>
