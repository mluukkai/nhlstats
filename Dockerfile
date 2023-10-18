FROM node:16

WORKDIR /usr/src/app

COPY . .

RUN npm ci

CMD npm start

# docker build -t toska/nhlstats .

# docker run -p 3001:3001 toska/nhlstats