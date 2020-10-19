library(data.table)
library(stringr)
library(plyr)
library(UsingR)
library(moments)
library("readr")
library(caret)
library(car)
library(rpart)
library(corrplot)
library(neuralnet)
library(nortest)
library("ggpubr")
library("ggthemes")
library('FNN')
library(rpart.plot)
library(BSDA)



#=====================================================
#   1.Leitura de ficheiro e tratar dados
#=====================================================

dados = read.csv2(file.choose()) # ler o arquivo
#setwd('/Users/joaoflores/Desktop/Joao/ISEP_3ANO/2Semestre/ANADI/TrabalhoPratico/TrabalhoPratico_II')
#dados=read.csv("dados_emprego.csv", sep=";", dec=",", stringsAsFactors = TRUE)
#dados=read.csv("dados_emprego.csv")
attach(dados) # eliminar o uso de $ na pesquisa
paragraficos=dados
length(dados)
summary(dados)
# Inspecionar dados
#criar data frame
dados<- data.frame(dados)

#normalizar os dados grau e emprego

#1= 12 ano
#2= Ate 12 ano
#3= Frequentou o ensino superior
#4= Grau avançado
#5= Licenciatura
dados$grau<- as.numeric(dados$grau)
dados$emprego<- as.numeric(dados$emprego)


#o emprego industria e industrial são o mesmo logo faz sentido atribuirmos o mesmo numero aos dois 
#Numeros atribuidos aos empregos 
#       1-industria 
#       2-industrial 
#       3-servicos

# dadosComValoresEmpregoAlterados = function(){
#   Uso=NULL
#   para_verifica=dados$emprego
#   for (j in 1: length(para_verifica)) {
#     if(para_verifica[j]==2){
#       Uso[j] <- 1
#     }else if(para_verifica[j]==3){
#       Uso[j] <- 2
#     }else{
#       Uso[j] <- 1
# 
#     }
#   }
#   return(Uso)
# }
# 
# dados$emprego<- dadosComValoresEmpregoAlterados()


#=====================================================
#   2.Faça um estudo da regressão linear entre a variável dependente (Salario) e a variável independente (Idade)
#=====================================================


# a) Calcule a correlação entre as variáveis Salário e Idade;


dados<- data.frame(dados)
cor(salario.,idade) #baixa corelação, isto porque existe uma fraca associação positiva entre as classificações

# b) Encontre a reta de regressão linear entre a variável dependente (Salário) e a variável independente (Idade);
slr.model=lm(salario.~idade,data=dados)
slr.model
#plot(idade,salario.,pch=20)
plot(salario.~idade,data=dados,pch=20,main="Regressão Linear entre salário e idade ")
abline(slr.model,col='red')



#c) 

#normalidade
residuals (slr.model)
shapiro.test(residuals(slr.model)) # nao se verifica a condição de normalidade porque o p-value é inferior a 0.01

#homocedasticidade
par(mfrow =c(2,1))
plot(fitted(slr.model),residuals(slr.model),xlab=" Val.Ajust.", ylab ="Res.",pch=20)
abline(h=0)
plot(idade,residuals(slr.model),xlab ="Idade", ylab ="Res.",pch=20)
abline(h=0)
mx=median(idade);
var.test(residuals(slr.model)[idade>mx],residuals(slr.model)[idade<mx]) 
#95 percent confidence interval: [1.237865 1.520529] -> deste modo,como o valor 1 não pertence ao invervalo, nada podemos concluir da homocedasticidade

#independência 
durbinWatsonTest(slr.model)  #se verifica a condição de independencia

#=====================================================
#  3.Encontre o modelo de regressão linear generalizado onde a variável dependente é o "Salario"
#  e as variáveis independentes são a "Idade", o "Grau" e o "Emprego". 
#  Com base no modelo encontrado, estime:
#=====================================================

mlr.model =lm(salario. ~ idade + grau + emprego, data = dados)
mlr.model
summary(mlr.model)$r.squared # coeficiente de determinacao, nao tao eficiente para fazer o predict
summary(mlr.model)$adj.r.squared # coeficiente de determinacao ajustado, nao tao eficiente para fazer o predict

#a) Uma pessoa com 32 anos, com o emprego de "Industrial", para todos osdiferentes graus de escolaridade;

requisitos= data.frame(idade=32,grau=1,emprego=1)
a=predict(mlr.model,requisitos)
requisitos= data.frame(idade=32,grau=2,emprego=1)
b=predict(mlr.model,requisitos)
requisitos= data.frame(idade=32,grau=3,emprego=1)
c=predict(mlr.model,requisitos)
requisitos= data.frame(idade=32,grau=4,emprego=1)
d=predict(mlr.model,requisitos)
requisitos= data.frame(idade=32,grau=5,emprego=1)
e=predict(mlr.model,requisitos)
df <- data.frame(var=c("12 ano", "Ate 12 ano", "Frequentou o ensino superior","Grau avançado","Licenciatura"), count=c(a,b,c,d,e))
ggplot(df, aes(x=var, y=count)) + geom_bar(stat = "identity")+ggtitle("Uma pessoa com 32 anos, com o emprego de Industria")
rm(a,b,c,d,e)
#b) Uma pessoa com 32 anos com o 12 ano para todos os diferentes tipos de emprego

requisitos= data.frame(idade=32,grau=1,emprego=1)
f=predict(mlr.model,requisitos)
requisitos= data.frame(idade=32,grau=1,emprego=2)
j=predict(mlr.model,requisitos)
requisitos= data.frame(idade=32,grau=1,emprego=3)
k=predict(mlr.model,requisitos)
df <- data.frame(var=c("Industria", "Industrial", "Serviços"), count=c(f,j,k))
ggplot(df, aes(x=var, y=count)) + geom_bar(stat = "identity")+ggtitle("Uma pessoa com 32 anos com o 12º ano escolaridade")
rm(f,j,k)
#=====================================================
#   4.Abrituto nivel conforme salario
#=====================================================

nivelConfomeSalario = function(){
  mediana=median(dados$salario.)
  nivel=NULL
  para_verifica=dados$salario.
  for (j in 1: length(para_verifica)) {
    if(para_verifica[j]>mediana){
      nivel[j] <- "Alto"
    }else {
      nivel[j] <- "Baixo"
    }
  }
  return(nivel)
}

dados$nivel<- nivelConfomeSalario()
paragraficos$nivel<- nivelConfomeSalario()
attach(dados) # eliminar o uso de $ na pesquisa relativamente ao novo atributo nivel

#=====================================================
#   5.Faça uma Análise Exploratória de Dados, usando os gráficos apropriados, 
#     de modo a analisar os vários atributos (numéricos e categóricos) do conjunto de dados.
#=====================================================
par(mfrow =c(1,1))
plot(salario.,idade,pch=20,main = "Relação entre salário e idade")
plot(salario.,grau,pch=20,main = "Relação entre salário e grau")
plot(salario.,emprego,pch=20,main = "Relação entre salário e emprego")

getmode <- function(v) {
  uniqv <- unique(v)
  uniqv[which.max(tabulate(match(v, uniqv)))]
}

maths_func <- function(title1, app1,par1){
  interactor <- title1
  x <- gregexpr("[0-9]+", interactor)
  x <- unlist(regmatches(interactor, x))
  x=strtoi(x, base = 0L)
  x
  resultado0 = writeLines(str_c("---",app1,"---"))
  resultado1 = writeLines(str_c("A avaliacao de ",par1))
  resultado7 = writeLines(str_c(" Respostas " , length(x)))
  resultado8 = writeLines(str_c(" Minimo: ", min(x) , " \n Maximo: " ,max(x)))
  resultado2 = writeLines(str_c(" Media: ",mean(x)))
  resultado2 = writeLines(str_c(" Mediana: ", median(x)))
  resultado3 = writeLines(str_c(" Moda: ", getmode(x)))
  resultado4 = writeLines(str_c(" Primeiro quartil é ", quantile(x)[2]," e o terceiro quartil é ",quantile(x)[4]))
  resultado6 = writeLines(str_c(" Distancia entre o primeiro e o terceiro quartil é de ", quantile(x)[4]-quantile(x)[2]))
  resultado7 = writeLines(str_c(" kurtosis: ",kurtosis(x)))
  resultado8 = writeLines(str_c(" skewness: ", skewness(x)))
}

graficos <- function(var,nome) {
  #criacao do grafico de Dispersão
  ggplot(data.frame(paragraficos), aes(x=var,fill=var))+
    geom_bar(stat = "count")+
    theme_minimal()+
    ggtitle(nome)+
    geom_text(stat = 'count',aes(label=..count..),vjust=2)
}



graficos(paragraficos$ano,"Grafico de barras de ano")
maths_func(ano,"ano","");
graficos(paragraficos$idade,"Grafico de barras da idade")
maths_func(idade,"idade","");
graficos(paragraficos$sexo,"Grafico de barras do sexo")
graficos(paragraficos$estado,"Grafico de barras do estado")
graficos(paragraficos$raca,"Grafico de barras da raça")
graficos(paragraficos$grau, "Grafico de barras do grau")
graficos(paragraficos$emprego, "Grafico de barras do emprego")
graficos(paragraficos$saude, "Grafico de barras da saude")
graficos(paragraficos$seguro, "Grafico de barras do seguro")
graficos(paragraficos$salario., "Grafico de barras do salario")
maths_func(salario.,"salario","");
graficos(paragraficos$nivel, "Grafico de barras do nivel")

#=====================================================
#   6.Usando o método k-fold cross validation estude a capacidade preditiva de alguns
#      métodos de previsão relativamente ao novo atributo Nivel:
#=====================================================

minmaxnorm <- function(x) {
  return ((x - min(x)) / (max(x) - min(x)))
}

minmaxdesnorm <- function(x,goal.attrib) { 
  return (x*(max(goal.attrib)-min(goal.attrib))+min(goal.attrib)) 
}

#prepara amostra

amostra <- sample(1:nrow(dados), 0.7*nrow(dados))
model.modelo <- dados[amostra,]
model.teste <- dados[-amostra,]

nivelConfomeSalario2 = function(){
  mediana=median(dados$salario.)
  nivel=NULL
  para_verifica=dados$salario.
  for (j in 1: length(para_verifica)) {
    if(para_verifica[j]>mediana){
      nivel[j] <- 0
    }else {
      nivel[j] <- 1
    }
  }
  return(nivel)
}

#a)
#cria arvore
#n folds para o for igual a 10
nrFolds <- 10
dados$nivel2<- nivelConfomeSalario2()
attach(dados) # eliminar o uso de $ na pesquisa relativamente ao novo atributo nivel
dados$nivel<-NULL
emprego.num<- dados
emprego.num$salario.<-NULL

cv.error <- matrix(nrow = nrFolds,ncol = 2)
empregoNumerico <- as.data.frame(lapply(emprego.num, as.numeric))

folds <- rep_len(1:nrFolds, nrow(empregoNumerico))
folds <- sample(folds, length(folds))

for(i in 1:nrFolds) {
  
  fold <- which(folds == i)
  emprego.treino <-empregoNumerico[fold,]
  emprego.teste <-empregoNumerico[-fold,]
  
  arvore <- rpart(nivel2 ~ . , data = emprego.treino, method ="class")
  prevs.modelo <- predict(arvore, emprego.teste)
  prevs.modelo <- minmaxdesnorm(prevs.modelo,emprego.num$nivel2)
  
  head(prevs.modelo)
  
  emprego.teste$nivel2 <- minmaxdesnorm(emprego.teste$nivel2,emprego.num$nivel2)
  cv.error[i,2] <- RMSE(prevs.modelo, emprego.teste$nivel2)
  
}
rpart.plot(arvore)




#b


#duplicação de dados --- dadosNumericos tem todos os atributos como numeric
dadosNumericos<-dados
dadosNumericos$estado<- as.numeric(dadosNumericos$estado)
dadosNumericos$raca<- as.numeric(dadosNumericos$raca)
dadosNumericos$saude<- as.numeric(dadosNumericos$saude)
dadosNumericos$seguro<- as.numeric(dadosNumericos$seguro)
dadosNumericos$sexo<- 1


dados.n <- dadosNumericos
dados.n$nivel2 <- as.numeric(dados$nivel)
dados.n$nivel <- NULL
#normalizacao ja com os Niveis numericos
dados.norm <- as.data.frame(lapply(dados.n, minmaxnorm))
dados.norm$sexo <- 1

apply(dados.norm,2,min)
apply(dados.norm,2,max)

#n folds para o for igual a 10
nrFolds <- 10

#var para a accuracy
accuracy1<-numeric()
#var para a recall
recall1 <- numeric()
#var para a precisão
precision1 <- numeric()
#var para f1
f11 <- numeric()



folds <- rep_len(1:nrFolds, nrow(dados.norm))
folds <- sample(folds, length(folds))

for(i in 1:nrFolds) {
  # actual split of the data
  fold <- which(folds == i)
  dataTeste.train <- dados.norm[fold, ]
  dataTeste.tst <- dados.norm[-fold, ]
  arvore <- rpart(nivel2 ~ ano + idade + sexo + estado + raca + grau + emprego + saude + seguro+ salario.,data = dataTeste.train, method="class")
  prevs.modelo <- predict(arvore, dataTeste.tst, type='class')
  
  head(prevs.modelo)
  
  m.conf<-table(dataTeste.tst$nivel,prevs.modelo)
  accuracy1[i] = (m.conf[1,1]+m.conf[2,2])/sum(m.conf)
  recall1[i] = m.conf[1,1]/(m.conf[1,1]+m.conf[1,2])
  precision1[i] = m.conf[1,1]/(m.conf[1,1]+m.conf[2,1])
  f11[i] = 2*precision1[i]*recall1[i]/(recall1[i]+precision1[i])
}


#b

#outras vars
#var para a accuracy
accuracy2<-numeric()
#var para a recall
recall2 <- numeric()
#var para a precisão
precision2 <- numeric()
#var para f1
f12 <- numeric()

numnodes <- c(6,2)

for(i in 1:nrFolds) {
  nn.model<-neuralnet(nivel2 ~ ano + idade + sexo + estado + raca + grau + emprego + saude + seguro+ salario., data = dataTeste.train, hidden = numnodes, threshold = 0.15)
  nnet.pred <- compute(nn.model,dataTeste.tst[,1:10])
  nnet.pred <- minmaxdesnorm(nnet.pred$net.result, dados.n$nivel2)
  dataTeste.tst$nivel2 <- minmaxdesnorm(dataTeste.tst$nivel2, dados.n$nivel2)
  nnet.pred <- as.factor(ifelse(nnet.pred<median(nnet.pred),"Alto","Baixo"))
  
  m.conf2 <- table(dataTeste.tst$nivel2,nnet.pred)
  
  accuracy2[i] = (m.conf2[1,1]+m.conf2[2,2])/sum(m.conf2)
  recall2[i] = m.conf2[1,1]/(m.conf2[1,1]+m.conf2[1,2])
  precision2[i] = m.conf2[1,1]/(m.conf2[1,1]+m.conf2[2,1])
  f12[i] = 2*precision2[i]*recall2[i]/(recall2[i]+precision2[i])
  
}
plot(nn.model)

#acabar amanha segunda parte da alinea b, comparações
accuracyDaNeural = mean(accuracy2)
recallDaNeural = mean(recall2)
precisionDaNeural = mean(precision2)
f1DaNeural = mean(f12)

resultado1 = writeLines(str_c("Accuracy Rede Neuronal ",accuracyDaNeural))
resultado2 = writeLines(str_c("Recall Rede Neuronal ",recallDaNeural))
resultado3 = writeLines(str_c("Precision Rede Neuronal ",precisionDaNeural))
resultado4 = writeLines(str_c("F1 Rede Neuronal ",f1DaNeural))


#c ( podemos fazer mais comparações para alem da media, como por exemplo do desvio, mediana entre outros)

#comparações entre a alinea a e b

accuracyDaArvore = mean(accuracy1)
recallDaArvore = mean(recall1)
precisionDaArvore = mean(precision1)
f1DaArvore = mean(f11)

resultado5 = writeLines(str_c("Accuracy Arvore ",accuracyDaArvore))
resultado6 = writeLines(str_c("Recall Arvore ",recallDaArvore))
resultado7 = writeLines(str_c("Precision  Arvore ",precisionDaArvore))
resultado8 = writeLines(str_c("F1 Rede Arvore ",f1DaArvore))

#d
#lilie test é para verificar a normalidade

#H0 testar normalidade entre a accuracy da arvore com a da neural
#H1 nao serve normalidade
#p-value <0.05 logo rejeita a hipotese 0
shapiro.test(accuracy1-accuracy2)
shapiro.test(recall1-recall2)
shapiro.test(precision1-precision2)
shapiro.test(f11-f12)

#H0 testar normalidade entre a accuracy da arvore com a da neural
#H1 nao serve normalidade
#p-value <0.05 logo rejeita a hipotese 0
lillie.test(accuracy1-accuracy2)
lillie.test(recall1-recall2)
lillie.test(precision1-precision2)
lillie.test(f11-f12)

#sao estramente assimetricos
#skewness(accuracy1-accuracy2)
#skewness(recall1-recall2)
#skewness(precision1-precision2)
#skewness(f11-f12)

#SIGN.test(accuracy1-accuracy2, paired = TRUE,alternative = "two.sided")
#SIGN.test(recall1-recall2, paired = TRUE,alternative = "two.sided")
#SIGN.test(precision1-precision2, paired = TRUE,alternative = "two.sided")
#SIGN.test(f11-f12, paired = TRUE,alternative = "two.sided")

#H0 existe diferença entre ambos
#H1 nao existe diferença entre ambos
#p-value < 0.05 rjeita H0
#wilcox.test(accuracy1-accuracy2)
#wilcox.test(recall1-recall2)
#wilcox.test(precision1-precision2)
#wilcox.test(f11-f12)

t.test(accuracy1,accuracy2, paired = TRUE,alternative = "two.sided")
t.test(recall1,recall2, paired = TRUE,alternative = "two.sided")
t.test(precision1,precision2, paired = TRUE,alternative = "two.sided")
t.test(f11,f12, paired = TRUE,alternative = "two.sided")

#=====================================================
#   7.Usando o método k-fold cross validation obtenha a previsão do atributo Salario com um modelo:   
#=====================================================
set.seed(123)
emprego.num<- dados
#emprego.num$nivel <- NULL
#emprego.num$salario <- emprego.num$salario

cv.error <- matrix(nrow = nrFolds,ncol = 2)
empregoNumerico <- as.data.frame(lapply(emprego.num, as.numeric))
emprego.norm <- as.data.frame(lapply(empregoNumerico, minmaxnorm))
emprego.norm$sexo<-1
emprego.norm$nivel2<-NULL


#a - K-vizinhos-mais-proximos e o valor de k mais adequado
index <- sample(1:nrow(emprego.norm), 0.7*nrow(emprego.norm))

data.train <- emprego.norm[index,-10]
data.tst <- emprego.norm[-index,-10]

train.salario <- emprego.norm[index,10]
tst.salario <- emprego.norm[-index,10]

k <- c()
rmseK <- c()
for(i in seq(1,50,1)) {
  knn.pred <- knn.reg(data.train,data.tst,train.salario, k = i)
  knn.pred$pred <- minmaxdesnorm(knn.pred$pred,emprego.num$salario)
  mmd <- minmaxdesnorm(tst.salario,emprego.num$salario)
  rmseK <- c(rmseK,RMSE(knn.pred$pred,mmd))
  k <- c(k,i)
}
min <-k[which.min(rmseK)]
cat("K min:", min)
plot(k,rmseK)

nrFolds <- 10
folds <- rep_len(1:nrFolds, nrow(emprego.norm))
folds <- sample(folds, length(folds))

for(i in 1:nrFolds){
  train.cv <- emprego.norm[folds != i,-10]
  test.cv <- emprego.norm[folds == i,-10]
  
  train.salario <- emprego.norm[folds != i,10]
  tst.salario <- emprego.norm[folds == i,10]
  
  knn.pred <- knn.reg(train.cv,test.cv,train.salario,k=min)
  knn.pred$pred <- minmaxdesnorm(knn.pred$pred,emprego.num$salario)
  mmd <- minmaxdesnorm(tst.salario,emprego.num$salario)
  cv.error[i,1] <- RMSE(knn.pred$pred,mmd)
}

#compararção de resultados
cv.error

#b
for(i in 1:nrFolds) {
  # actual split of the data
  
  fold <- which(folds == i)
  emprego.treino <- emprego.norm[fold,]
  emprego.teste <- emprego.norm[-fold,]
  
  arvore <- rpart(salario. ~ . ,data = emprego.treino, method="anova")
  prevs.modelo <- predict(arvore, emprego.teste)
  prevs.modelo <- minmaxdesnorm(prevs.modelo,emprego.num$salario.)
  
  emprego.teste$salario. <- minmaxdesnorm(emprego.teste$salario.,emprego.num$salario.)
  cv.error[i,2] <- RMSE(prevs.modelo, emprego.teste$salario.)
  
}
rpart.plot(arvore)
#text(arvore,digits = 3,cex=0.65,font=10,pretty=0,fance=T,fwidth = 0,fheight = 0)

#c - Obtenha a média e o desvio padrão do RMSE dos modelos
cv.error
apply(cv.error,2,mean)
apply(cv.error,2,sd)

mediaK <- mean(cv.error[,1])
mediaA <- mean(cv.error[,2])

sdK <- sd(cv.error[,1])
sdA <- sd(cv.error[,2])

media <- mediaK - mediaA
sd <- sdK - sdA

resultado9 = writeLines(str_c("Media K-vizinhos ",mediaK))
resultado10 = writeLines(str_c("Media Arvore de Regressão ",mediaA))

resultado11 = writeLines(str_c("Desvio Padrão K-vizinhos ",sdK))
resultado12 = writeLines(str_c("Desvio Padrão de Regressão ",sdA))

#d

shapiro.test(rmseK - cv.error[,2] )

lillie.test(rmseK - cv.error[,2])

SIGN.test(rmseK - cv.error[,2], paired = TRUE,alternative = "two.sided")

#H0 existe diferença entre ambos
#H1 nao existe diferença entre ambos
#p-value < 0.05 rjeita H0
wilcox.test(accuracy1-accuracy2)

#t.test(accuracy1,accuracy2, paired = TRUE,alternative = "two.sided")