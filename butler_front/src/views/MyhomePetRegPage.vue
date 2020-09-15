<template>
    <v-card class="overflow-hidden" flat="flat">
        <v-app-bar
            absolute="absolute"
            color="#b5c7d3"
            height="78"
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
          <v-flex>반려 동물 등록</v-flex>
        </v-flex>
      </v-flex>
        </v-app-bar>
        <v-sheet id="scrolling-techniques-7" class="overflow-y-auto" max-height="600">
            <div>
                <v-container fluid="fluid" grid-list-xl="grid-list-xl">
                    <v-layout row="row" wrap="wrap">
                        <v-flex sm8="sm8" offset-sm-2="offset-sm-2">
                            <v-card class="mt-12 pt-8" exact="exact" flat="flat">
                                <v-card class="pt-2 pb-4 justify-center text-center" color="#b5c7d3">
                                    <v-btn
                                        text="text"
                                        fab="fab"
                                        :disabled="dialog"
                                        @click="imgUploadDialog = true; dialog = true">
                                        <v-avatar size="64">
                                            <v-img max-width="70px" :src="userImg || userDefaultImg" alt="userImg"></v-img>
                                        </v-avatar>
                                    </v-btn>
                                </v-card>
                                <v-card
                                    tile="tile"
                                    outlined="outlined"
                                    tag="div"
                                    width="100%"
                                    height="360"
                                    class="pt-3 pl-7 pr-7"
                                    flat="flat">
                                    <v-card flat="flat">                                        
                                        <v-content class="font-weight-bold pt-2">반려동물의 이름은 무엇인가요?</v-content>
                                        <v-text-field v-model="name" label="Name" required="required"></v-text-field>
                                    </v-card>
                                    <p v-if="loading==true">
                                        <v-progress-circular
                                        :size="25"
                                        color="amber"
                                        indeterminate
                                        ></v-progress-circular>
                                        사진을 판별중입니다.
                                    </p>
                                    <v-card flat="flat" v-if="select_list.length>0">
                                        <v-stepper v-model="e1">

                                            <v-stepper-items>
                                                <v-stepper-content step="1">
                                                    <v-card flat="flat" height="180">
                                                        <v-content>키우고 싶은 품종이 맞나요?
                                                        </v-content>
                                                        <v-divider></v-divider>
                                                        <!-- <div v-for="(l,index) in select_list" :key="index"> -->
                                                            <v-select
                                                                :items="select_list"
                                                                :menu-props="{ bottom: true, offsetY: true }"
                                                                v-model="select"
                                                                label="Breed"
                                                            ></v-select>
                                                        <!-- </div> -->
                                                        <div v-if="select!=null">
                                                                <v-content>네!
                                                                    <strong class="success--text">{{select}}</strong>예요!</v-content>
                                                            </div>

                                                        <br>
                                                            <v-btn color="#7BC243" class="white--text mr-3" @click="e1 = 2">
                                                                아니요
                                                            </v-btn>
                                                        </v-card>

                                                    </v-stepper-content>

                                                    <v-stepper-content step="2">
                                                        <v-card flat="flat" height="180">
                                                            앗.. 실수!
                                                            <v-icon color="#7BC243">mdi-emoticon-tongue-outline</v-icon>
                                                            <v-content class="font-weight-bold">누구와 함께할건가요?</v-content>
                                                            <v-btn class="mx-4 mt-6" fab="fab" @click="fk_animal_id = 1, select='반려견'">
                                                                <v-img src="../assets/dog.png" max-width="80px"/>
                                                            </v-btn>
                                                            <v-btn class="mx-10 mt-6" fab="fab" @click="fk_animal_id = 2, select='반려묘'">
                                                                <v-img src="../assets/cat.png" max-width="80px"/>
                                                            </v-btn>
                                                            <div v-if="select!=null">
                                                                <v-content class="mt-8">물론
                                                                    <strong class="success--text">{{select}}</strong>이죠!</v-content>
                                                            </div>

                                                        </v-card>
                                                    </v-stepper-content>

                                                </v-stepper-items>
                                            </v-stepper>
                                        </v-card>

                                    </v-card>
                                </v-card>
                                <v-btn block="block" class="mt-4" color="#b5c7d3" dark="dark" @click="regPet">등록하기</v-btn>
                                <!-- <br> -->
                            </v-flex>
                        </v-layout>
                    </v-container>

                    <v-dialog
                        v-model="imgUploadDialog"
                        persistent="persistent"
                        width="300"
                        z-index:2="z-index:2">
                        <v-card width="300">
                            <v-container>
                                <v-flex>
                                    <p class="ml-2 font-weight-bold">반려동물 사진</p>
                                    <form @submit.prevent="requestImgUpload">
                                        <v-row class="justify-front ml-2 mr-2">
                                            <v-divider></v-divider>
                                            <v-btn width="280" text="" type="file" @click.prevent="$refs.userImg.click()">사진 가져오기</v-btn>
                                            <v-divider></v-divider>
                                            <input v-show="false" ref="userImg" type="file" @change="onFileChange"/>
                                            <p class="mb-3 mt-3 ml-3">사진은 20MB 이내의 jpg, png, gif 파일만 등록 가능합니다.</p>
                                        </v-row>
                                        <div class="d-flex justify-end">
                                            <v-btn
                                                tile="tile"
                                                depressed="depressed"
                                                type="submit"
                                                color="#7BC243"
                                                class="white--text mr-3"
                                                @click="imgUploadDialog = false">등록</v-btn>
                                            <v-btn
                                                tile="tile"
                                                depressed="depressed"
                                                color="#BDBDBD"
                                                class="white--text"
                                                @click="imgUploadDialog = false">취소</v-btn>
                                        </div>
                                    </form>
                                </v-flex>
                            </v-container>
                        </v-card>
                    </v-dialog>
                    <v-dialog
                        v-model="dialog"
                        hide-overlay="hide-overlay"
                        persistent="persistent"
                        z-index:1="z-index:1">
                        <v-card>
                            <v-card-text>
                                <v-img src="../assets/3Dto2D.png" max-width="300px"/>
                            </v-card-text>
                        </v-card>
                    </v-dialog>
            </div>
        </v-sheet>
    </v-card>
</template>
<script>
    import { mapGetters } from "vuex";
    export default {
        name: "MyhomePetRegPage",
        data() {
            return {
                loading: false,
                profileDialog: false,
                imgUploadDialog: false,
                statusColor: 'blue',
                statusIcon: 'fa-info-circle',
                userImg:  "",
                uploadImg: "",
                userDefaultImg: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__3" +
                        "40.png",
                dialog: false,
                name: '',
                fk_animal_id: '',
                select: null,
                select_list: [],
                e1: 1,
                index: 0,
            }
        },
        watch: {
            dialog(val) {
                if (!val) 
                    return

                setTimeout(() => (this.dialog = false), 40)
            }
        },
        computed: {
            check_animalId(val) {
                if(val=="강아지")
                    return 1;
                if(val=="고양이")
                    return 2;
                if(val=="말티즈")
                    return 3;
                if(val=="골든 리트리버")
                    return 4;
                if(val=="러시안 블루")
                    return 5;
                if(val=="페르시안")
                    return 6;
            },
            ...mapGetters(["requestHeader", "user", "getAxios"])
        },        
        methods: {
            deleteImg() {
                this.userImg = this.userDefaultImg;
                this.uploadImg = null;
            },
            onFileChange(e) {
                var files = e.target.files || e.dataTransfer.files;

                if (!files.length) {
                    return;
                }
                var fileName = "";
                fileName = files[0].name;

                var reg = /(.*?)\.(jpg|jpeg|png|gif)$/;

                if (!fileName.match(reg)) {
                    alert("jpg, jpeg, png, gif 중 하나만 올려주세요");
                    return;
                }
                this.uploadImg = files[0];
                this.createImage(files[0]);
            },
            // 이미지 바꿔서 보여주는 부분
            createImage(file) {
                var image = new Image();
                var reader = new FileReader();
                var vm = this;

                reader.onload = e => {
                    vm.userImg = e.target.result;
                };
                reader.readAsDataURL(file);
            },
            requestImgUpload() {
                if (this.uploadImg !== null) {
                    if (this.uploadImg.size >= 1024 * 1024 * 10) {
                        alert("10MB 이내의 파일을 올려주세요");
                        return;
                    }

                    const form = new FormData()
                    form.append("file",this.uploadImg)

                    this.getAxios
                        .post("/image/upload/"+this.$store.getters.isLoggedIn.email,form)
                        .then(res => {
                            this.loading=true

                            this.getAxios
                        .get("/myhome/animal/distinct/"+this.$store.getters.isLoggedIn.email)
                        .then(res => {
                            this.select_list=res.data.res
                            this.loading=false
                        })
                        .catch(err => {
                            console.log(err)
                        })
                        })
                        .catch(err => {
                            console.log(err)
                        })
                    }
            },
            getImg() {
                if (this.user.userImg != null && this.user.userImg != "") {
                    // this.userImg = "https://e02lib1.p.ssafy.io/image" + this.user.userImg
                    this.userImg = "http://localhost:8080/image/" + this.user.userImg
                } else {
                    this.userImg = this.userDefaultImg
                }
            },
            regPet() {
                if (this.uploadImg == '') {
                    alert("반려친구의 사진을 등록해 주세요.")
                } else if (this.select === null) {
                    alert("반려친구는 누구인가요?")
                } else if (this.name == '') {
                    alert("반려친구의 이름을 지어주세요.")
                } else {
                    var user = {
                        email: this.$store.getters.isLoggedIn.email,
                        fk_animal_id: check_animalId(this.select),
                        animal_name: this.name,
                    };
                    alert(user.fk_animal_id)
                    this.getAxios
                        .post("/myhome/animal/update",user)
                        .then(res => {
                            alert("반려동물 등록하기")
                        })
                        .catch(err => {
                            console.log(err)
                        })
                    
                    this.getAxios
                        .post("/mission/init",user)
                        .then(res => {
                            alert("미션생성하기")
                        })
                        .catch(err => {
                            console.log(err)
                        })

                    alert("등록이 완료되었습니다.")
                    this
                        .$router
                        .push('/myhome')
                }
            }
        },
        mounted() {
            this.getImg()
        }
    }
</script>