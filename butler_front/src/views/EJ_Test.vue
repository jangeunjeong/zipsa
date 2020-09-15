<template>
  <v-row align="center" class="mt-12 pt-12">
    <v-col cols="12">
      <v-select
        :items="items"
        :menu-props="{ bottom: true, offsetY: true }"
        v-model="select"
        label="Label"
      ></v-select>
      {{select}}
    </v-col>
  </v-row>
</template>

<script>
  export default {
    data: () => ({
      items: ['Foo', 'Bar', 'Fizz', 'Buzz'],
      select: '',
    }),
  }
</script>