<template>
    <v-card class="overflow-hidden" flat>
        <v-app-bar
            absolute="absolute"
            color="#b5c7d3"
            height= "78"
            elevate-on-scroll="elevate-on-scroll"
            scroll-target="#scrolling-techniques-7"
            centerTitle:="centerTitle:"
            true="true">
            <v-spacer></v-spacer>
            <v-flex xs12>
        <v-flex d-flex class="text-center py-2">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>마이홈</v-flex>
        </v-flex>
      </v-flex>
            <v-spacer></v-spacer>
    </v-app-bar>
    <v-sheet id="scrolling-techniques-5" class="overflow-y-auto" max-height="600">
    <v-container fluid="fluid" grid-list-xl="grid-list-xl">
        <v-card class="mb-10" exact="exact" flat="flat">
                <v-layout row="row" wrap="wrap">
                    <v-flex x12="x12" class="pt-12">
                        <v-content class="font-weight-bold mt-8 pt-8 pb-2">프로필</v-content>
                        <router-link :to="{name:'MyhomeSettingPage'}" style="text-decoration:none">
                            <v-card min-width="300px" >
                                <v-content class="justify-center text-center pl-10">{{nickname}}<strong class="indigo--text pl-6">>>
                                        </strong></v-content>
                            </v-card>
                        </router-link>
                    </v-flex>
                    <v-flex x12="x12" class="pt-6">
                        <v-content class="font-weight-bold pt-2 pb-2">나의 반려동물</v-content>
                        <v-card min-width="300px" outlined>
                            <v-tabs height="200px" v-model="tab">
                                <v-tab v-if="(animals.length==0)">
                                    <v-img src="../assets/defaultPet.png" max-width="300px"/>
                                </v-tab>
                                <v-tab v-for="(animal,n) in animals" :key="n">
                                    <div @click="testtest(n)">
                                        <img :src="animal.img" class="pr-12" style="height: 200px;"/>
                                    </div>
                                </v-tab>
                            </v-tabs>
                            <v-tab v-if="possible">
                            <v-card-text class="text-center">
                                <router-link :to="{name:'MyhomePetRegPage'}" style="text-decoration:none">
                                    <v-content class="black--text">
                                        <strong class="indigo--text">+
                                        </strong>펫 추가 등록</v-content>
                                </router-link>
                            </v-card-text></v-tab>
                        </v-card>
                    </v-flex>
                </v-layout>
                </v-card>
    </v-container>
    </v-sheet>
  </v-card>
</template>

<script>
    import { mapGetters } from "vuex";
    export default {
        name: "MyhomePage",
        data: () => ({
            tab: '',
            dialog: false,
            email: '',
            nickname: '',
            possible: false,
            certi_have: false,
            animals: [],
        }),
        watch: {
            length(val) {
                this.tab = val - 1
            }
        },
        computed: {
            ...mapGetters(["requestHeader", "user", "getAxios"])
        },
        mounted() {
            this.getAxios
            .get("/myhome/"+this.$store.getters.isLoggedIn.email)
            .then(res => {
                this.nickname=res.data.nickname
                this.certi_have=res.data.certi_have
                this.animals=res.data.certifiedAnimals
                this.animals.push({id: res.data.nowAnimal.id,name:res.data.animal_name ,breed: res.data.nowAnimal.breed ,img: res.data.nowAnimal.img})
                console.log(res)

                if(res.data.certi_have=="TRUE"){
                    this.certi_have=true
                }else{
                    this.certi_have=false
                }
                if(this.animals.length==0 ||this.certi_have){
                    this.possible=true
                } else{
                    this.possible=false
                }   
            })
            .catch(err => {
                alert(err);
            });
             
        },
        methods: {
            goBack() {
                this.$router.push("/main")
            },
            testtest(n) {
                if(this.animals.length==0||n<this.animals.length-1){
                      this.certi_have=true;
                  }
                this.$router.push({
              name: "MyhomePetDetailPage",
              params: {
                  id: this.animals[n].id,
                  name: this.animals[n].name,
                  breed: this.animals[n].breed,
                  img: this.animals[n].img,
                  certi_have: this.certi_have,
              }
            });
            },
        }
    };
</script>