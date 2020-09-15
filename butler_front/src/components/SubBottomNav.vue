<template>
  <v-app-bar
    dense
    flat
    color="rgba(0, 0, 0, 0)"
    style="position: fixed; z-index: 5000; max-width: 500px;"
    class="mx-auto"
  >
    <v-flex d-flex class="px-0 pt-0">
      <v-flex v-for="link in links" :key="link.title">
        <v-btn :to="link.to" icon tile>
          <v-flex
            class="d-flex flex-column align-center"
            style="position: absolute; animation: mymove; animation-duration: 1s"
          >
            <!-- <img src="../assets/학습하기.png" /> -->
            <v-avatar class="ma-0 pa-0" color="#FFFFFF">
              <img :src="require(`@/assets/${link.fileName}`)" style="width: 55%; height: 55%" />
            </v-avatar>
            <p class="ma-0 pa-0 caption" style="color: #FFFFFF">{{ link.title }}</p>
          </v-flex>
        </v-btn>
      </v-flex>
    </v-flex>
  </v-app-bar>
</template>

<script>
export default {
  name: "SubBottomNav",
  props: {
    links: Array
  },
  computed: {},
  data() {
    return {};
  },
  computed: {},
  watch: {},
  methods: {},
  mounted() {}
};
</script>

<style>
.v-btn:before {
  background-color: white;
}
@keyframes mymove {
  from {
    bottom: -100px;
  }
  to {
    bottom: -35px;
  }
}
</style>