FROM node
# set working directory
WORKDIR /app
# install app dependencies
COPY package.json ./
COPY package-lock.json ./
# add app
COPY . ./
RUN npm install
EXPOSE 3000
# start app
CMD ["npm", "start"]
