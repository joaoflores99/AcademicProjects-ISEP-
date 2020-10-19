library(data.table)
library(stringr)
library(plyr)
library(UsingR)
library(moments)
library("readr")

# 1 Ler os arquivos

dados = read_csv(file.choose()) # ler o arquivo
attach(dados) # eliminar o uso de $ na pesquisa




# Inspecionar dados

# Analisar Generos

total_genero=length(Género) # contar o total
contador_de_generos=count(Género) # ver a frequencia de cada um
percentagem_Genero=round(contador_de_generos$freq/length(Género)*100,2) # fazer a percentagens dos generos
percentagem_Genero=paste(percentagem_Genero,"%")
#criacao do grafico circular para Generos
table_Genero=table(Género) #Colocar numa tabela
pie(table_Genero,main = "Gráfico Circular De Generos",labels=percentagem_Genero,col = c(4,2)) #grafico
legendaFeminino=str_c("Feminino"," - ",contador_de_generos[1,2]," pessoa(s)")
legendaMasculino=str_c("Masculino"," - ",contador_de_generos[2,2]," pessoa(s)")
legend("topright", fill = c(4,2),legend=c(legendaFeminino,legendaMasculino)) # Legenda para o grafico



#Analisar Idades

total_idade=length(Idade) # contar o total
contador_de_idades=count(Idade) # ver a frequencia de cada um

#criacao do grafico de barra para Idade
table_Idade=table(Idade) # colocar os dados numa tabela
barplot(table_Idade,main = "Gráfico De Barras De Idades",col=c(8), xlab = "Idades",ylab = "Ocorrências", ylim = c(0,max(contador_de_idades$freq))) #grafico


#criacao do grafico circular para Idade


percentagem_Idades=round(contador_de_idades$freq/length(Idade)*100,2)
percentagem_Idades=paste(percentagem_Idades,"%")
legenda_idade<-paste(attributes(table_Idade)$dimnames$Idade," anos")
legenda_idade<-paste(legenda_idade,percentagem_Idades, sep = " - ")
pie(table_Idade,main = "Gráfico Circular De Idades",col = rainbow(length(table_Idade))) #grafico
legend("topright",legend=legenda_idade, fill = rainbow(length(table_Idade)))



#criacao do grafico de Dispersão para Idade
# plot(count(Idade)$x,count(Idade)$freq,ylab = "Ocorrências",xlab = "Idades", main = "Grafico de Dispersão de Idades",pch=20, type = "h") # com barras
plot(count(Idade)$x,count(Idade)$freq,ylab = "Ocorrências",xlab = "Idades", main = "Grafico de Dispersão de Idades",pch=20) # com pontos


# ======================================================================================================================
#                                                             utilizacao de qual plataforma
# ======================================================================================================================

utilizou_alguma_plataforma= `Alguma vez já utilizou a plataforma(web/aplicação mobile)?`
table_utilizou_alguma_plataforma=table(utilizou_alguma_plataforma) # colocar os da
contador_de_ut_pd=count(utilizou_alguma_plataforma)
percentagem_u_pd=round(contador_de_ut_pd$freq/length(utilizou_alguma_plataforma)*100,2)
percentagem_u_pd=paste(percentagem_u_pd,"%")
legenda_u_pd<-paste(attributes(table_utilizou_alguma_plataforma)$dimnames$utilizou_alguma_plataforma)
legenda_u_pd<-paste(legenda_u_pd,percentagem_u_pd, sep = " - ")
pie(table_utilizou_alguma_plataforma,main = "Gráfico Circular de utilização Pingo Doce",col = rainbow(length(table_utilizou_alguma_plataforma))) #grafico
legend("topright",legend=legenda_u_pd, fill = rainbow(length(table_utilizou_alguma_plataforma)))

utilizou_alguma_plataforma_c= `Alguma vez já utilizou a plataforma(web/aplicação mobile)?_1`
table_utilizou_alguma_plataforma_c=table(utilizou_alguma_plataforma_c) # colocar os da
contador_de_ut_pd_c=count(utilizou_alguma_plataforma_c)
percentagem_u_pd_c=round(contador_de_ut_pd_c$freq/length(utilizou_alguma_plataforma_c)*100,2)
percentagem_u_pd_c=paste(percentagem_u_pd_c,"%")
legenda_u_pd_c<-paste(attributes(table_utilizou_alguma_plataforma_c)$dimnames$utilizou_alguma_plataforma)
legenda_u_pd_c<-paste(legenda_u_pd_c,percentagem_u_pd_c, sep = " - ")
pie(table_utilizou_alguma_plataforma_c,main = "Gráfico Circular de utilização Continente",col = rainbow(length(table_utilizou_alguma_plataforma_c))) #grafico
legend("topright",legend=legenda_u_pd_c, fill = rainbow(length(table_utilizou_alguma_plataforma_c)))


# ======================================================================================================================
#                                                             Genericos
# ======================================================================================================================

graficos_barplot <- function(title1,table1,app1,param1) {
  numero_respostas_totais = length(title1) #numero total de respostas
  contador = count(title1) #contador das respostas
  #criacao do grafico de barras para Frequencia de Uso
  barplot(table1,main = str_c("Grafico de barras de frequencia de uso da app ", app1),col=c(8), xlab = "Frequencia", ylab = "Ocorrencia", xlim = c(0,5), ylim = c(0,max(contador$freq))) #grafico para o uso
}

grafico_pie <- function(title1,table1,app1,param1,legend1) {
  
  contador= count(title1) #conta
  title1=na.omit(title1)
  numero_respostas_totais = length(title1) #numero total de respostas
  
  percentagem_respostas=round(contador$freq/numero_respostas_totais*100,2) # fazer a percentagens dos generos
  percentagem_respostas=paste(percentagem_respostas,"%")
  
  #criacao do grafico circular
  pie(table1,main = str_c("Grafico circular do ",param1," da app ",app1),col = rainbow(length(table1)),labels=c(percentagem_respostas)) #grafico
  legend( "topright",legend=legend1, fill = rainbow(length(table1)))
  
  # =======================================================
}

grafico_plot <- function(title1,table1,app1,param1) {
  #criacao do grafico de Dispersão
  # plot(count(title1)$x,count(title1)$freq,ylab = "Ocorrencias?",ylim=c(0,10) ,xlim=c(1,5),xlab = param1, main = str_c("Grafico de Dispersao ",param1,"  da app ",app1),pch=10, type = "h") # com barras
  plot(count(title1)$x,count(title1)$freq,ylab = "Ocorrencias",ylim=c(0,10) ,xlim=c(1,5), xlab = param1, main = str_c("Grafico de Dispersao ",param1,"  da app ",app1),pch=20) # com pontos
}

grafico_boxplot <- function(f1,f2,f3,f4,f5,f6,nomes,app) {
  interactor1 <- f1
  x1 <- gregexpr("[0-9]+", interactor1)
  x1 <- unlist(regmatches(interactor1, x1))
  x1=strtoi(x1, base = 0L)
  x1
  interactor2 <- f2
  x2 <- gregexpr("[0-9]+", interactor2)
  x2 <- unlist(regmatches(interactor2, x2))
  x2=strtoi(x2, base = 0L)
  x2
  interactor3 <- f3
  x3 <- gregexpr("[0-9]+", interactor3)
  x3 <- unlist(regmatches(interactor3, x3))
  x3=strtoi(x3, base = 0L)
  x3
  interactor4 <- f4
  x4 <- gregexpr("[0-9]+", interactor4)
  x4 <- unlist(regmatches(interactor4, x4))
  x4=strtoi(x4, base = 0L)
  x4
  interactor5 <- f5
  x5 <- gregexpr("[0-9]+", interactor5)
  x5 <- unlist(regmatches(interactor5, x5))
  x5=strtoi(x5, base = 0L)
  x5
  interactor6 <- f6
  x6 <- gregexpr("[0-9]+", interactor6)
  x6 <- unlist(regmatches(interactor6, x6))
  x6=strtoi(x6, base = 0L)
  x6
  boxplot(x1,x2,x3,x4,x5,x6,names=nomes,main=app,ylab="Ocurrencias")
  
}


tirar_os_NAS = function(title1){
  interactor <- title1
  x <- gregexpr("[0-9]+", interactor)
  x <- unlist(regmatches(interactor, x))
  title1=strtoi(x, base = 0L)
  return(title1)
}

Tempo_de_uso = function(dadoscontados){
  Uso=NULL
  para_verifica=dadoscontados$tempo_frequencia
  for (j in 1: length(para_verifica)) {
    if(para_verifica[j]==1){
      Uso[j] <- "Raramente"
    }else if(para_verifica[j]==2){
      Uso[j] <- "Mensalmente"
    }else if(para_verifica[j]==3){
      Uso[j] <- "Semanalmente"
    }else if(para_verifica[j]==4){
      Uso[j] <- "2/3 Semanalmente"
    }else if(para_verifica[j]==5){
      Uso[j] <- "Diariamente"
    }else{}
  }
  return(Uso)
}


#                                       Medidas descritivas de localização, dispersão e forma
satisfacaoFrequencia <- function(nota_do_site,tempo_frequencia,title){
  dataframe=data.frame(nota_do_site,tempo_frequencia)
  dadoscontados=count(dataframe)
  Uso=Tempo_de_uso(dadoscontados)
  Nota=paste(dadoscontados$nota_do_site)
  Frequencia=dadoscontados$freq
  # Grouped
  ggplot(dadoscontados, aes(fill=Uso, y=Frequencia, x=Nota)) +
    geom_bar(position="dodge", stat="identity")+
    ggtitle("Grau de Satisfação e a Frequencia de uso do site ",title) +
    xlab("Nota do site") + ylab("Ocorrência")
}

cv<-function(x){coef<-sd(x)/mean(x)*100
return(coef)}
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
  resultado3 = writeLines(str_c(" Moda: ", getmode((x))))
  resultado4 = writeLines(str_c(" Primeiro quartil é ", quantile(x)[2]," e o terceiro quartil é ",quantile(x)[4]))
  resultado5 = writeLines(str_c(" Varianca é ", round(var(x),digits=3),", o desvio padrão é ",round(sd(x),digits=3)," e o coeficiente de variação é de ",round(cv(x),digits=3)))
  resultado6 = writeLines(str_c(" Distancia entre o primeiro e o terceiro quartil é de ", quantile(x)[4]-quantile(x)[2]))
  
}

VerificarClassifica = function(para_verifica){
  legenda=NULL
  for (j in 1: length(para_verifica)) {
    if(para_verifica[j]==0){
      legenda[j] <- "Nada"
    }else if(para_verifica[j]==1){
      legenda[j] <- "Muito baixo"
    }else if(para_verifica[j]==2){
      legenda[j] <- "Baixo"
    }else if(para_verifica[j]==3){
      legenda[j] <- "Normal"
    }else if(para_verifica[j]==4){
      legenda[j] <- "Alto"
    }else if(para_verifica[j]==5){
      legenda[j] <- "Muito alto"
    }else{}
    
  }
  return(legenda)
}

# ======================================================================================================================
# ======================================================================================================================
#                                                             Geral
# ======================================================================================================================
# ======================================================================================================================
app_geral="Geral"
# ====================================================
#    1-> Idade
# ====================================================
idade=`Idade`
table_idade=table(idade)
maths_func(idade,app_geral,"idade")
# ======================================================================================================================
# ======================================================================================================================
#                                                             Continente
# ======================================================================================================================
# ======================================================================================================================

app_continente="Continente"

# ====================================================
#    1-> FREQUENCIA DE USO DA PLATAFORMA Continente
# ====================================================
frequencia_de_uso_da_app_continente=`Com que frequência utiliza a plataforma?_1`
table_frequencia_de_uso_continente=table(frequencia_de_uso_da_app_continente)
maths_func(frequencia_de_uso_da_app_continente,app_continente,"frequencia")
graficos_barplot(frequencia_de_uso_da_app_continente,table_frequencia_de_uso_continente,app_continente,"Frequencia de Uso")

legendc1=NULL
para_verifica=count(tirar_os_NAS(frequencia_de_uso_da_app_continente))$x
for (j in 1: length(para_verifica)) {
  if(para_verifica[j]==1){
    legendc1[j] <- "Raramente"
  }else if(para_verifica[j]==2){
    legendc1[j] <- "Mensalmente"
  }else if(para_verifica[j]==3){
    legendc1[j] <- "Semanalmente"
  }else if(para_verifica[j]==4){
    legendc1[j] <- "2/3 Semanalmente"
  }else if(para_verifica[j]==5){
    legendc1[j] <- "Diariamente"
  }else{}
}

grafico_pie(frequencia_de_uso_da_app_continente,table_frequencia_de_uso_continente,app_continente,"Frequencia de Uso",legendc1)
grafico_plot(frequencia_de_uso_da_app_continente,table_frequencia_de_uso_continente,app_continente,"Frequencia de Uso")




# =============================================
#       2-> RECOMENDACÃO DA APP Continente
# =============================================
Recomendacao_da_app_continente= `Recomendaria a plataforma a familiares e amigos?_1`
table_Recomendacao_da_app_continente=table(Recomendacao_da_app_continente)
maths_func(Recomendacao_da_app_continente,app_continente,"recomendacao")
graficos_barplot(Recomendacao_da_app_continente,table_Recomendacao_da_app_continente,app_continente,"Recomendação")
para_verifica=count(tirar_os_NAS(Recomendacao_da_app_continente))$x
legendc2=VerificarClassifica(para_verifica)
grafico_pie(Recomendacao_da_app_continente,table_Recomendacao_da_app_continente,app_continente,"Recomendação",legendc2)
grafico_plot(Recomendacao_da_app_continente,table_Recomendacao_da_app_continente,app_continente,"Recomendação")
# =============================================
# 3-> INTUICAO DA APP CONTINENTE
# =============================================
Intuicao_da_app_continente=`A plataforma(web/aplicação mobile) é intuitiva?_1`
table_Intuicao_da_app_continente=table(Intuicao_da_app_continente)
maths_func(Intuicao_da_app_continente,app_continente,"intuicao")
graficos_barplot(Intuicao_da_app_continente,table_Intuicao_da_app_continente,app_continente,"Intuição")
para_verifica=count(tirar_os_NAS(Intuicao_da_app_continente))$x
legendc3=VerificarClassifica(para_verifica)
grafico_pie(Intuicao_da_app_continente,table_Intuicao_da_app_continente,app_continente,"Intuição",legendc3)
grafico_plot(Intuicao_da_app_continente,table_Intuicao_da_app_continente,app_continente,"Intuição")
# =============================================
# 4-> NOTA DA APP CONTINENTE
# =============================================
Nota_da_app_continente=`De 0 a 5, que nota atribui ao site?_1`
table_Nota_da_app_continente=table(Nota_da_app_continente)
maths_func(Nota_da_app_continente,app_continente,"nota da app")
graficos_barplot(Nota_da_app_continente,table_Nota_da_app_continente,app_continente,"Nota Geral")
para_verifica=count(tirar_os_NAS(Nota_da_app_continente))$x
legendc4=VerificarClassifica(para_verifica)
grafico_pie(Nota_da_app_continente,table_Nota_da_app_continente,app_continente,"Nota Geral",legendc4)
grafico_plot(Nota_da_app_continente,table_Nota_da_app_continente,app_continente,"Nota Geral")
# =============================================
# 5-> TEMPO UTILIZACAO PLATAFORMA CONTINENTE
# =============================================
Tempo_utilizacao_da_app_continente=`Quanto tempo passa usando a plataforma?_1`
table_Tempo_utilizacao_da_app_continente=table(Tempo_utilizacao_da_app_continente)
maths_func(Tempo_utilizacao_da_app_continente,app_continente,"tempo de utilizacao")
legendc5=c(attributes(table_Tempo_utilizacao_da_app_continente)$dimnames$Tempo_utilizacao_da_app_continente)# Legenda para o grafico
graficos_barplot(Tempo_utilizacao_da_app_continente,table_Tempo_utilizacao_da_app_continente,app_continente,"tempo de utilizacao")
grafico_pie(Tempo_utilizacao_da_app_continente,table_Tempo_utilizacao_da_app_continente,app_continente,"tempo de utilizacao",legendc5)
grafico_plot(Tempo_utilizacao_da_app_continente,table_Tempo_utilizacao_da_app_continente,app_continente,"tempo de utilizacao")
# ==========================================================
# 6->  TEMPO DE ASSISTENCIA TECNICA DA PLATAFORMA CONTINENTE
# =========================================================
Tempo_assistencia_tecnica_da_app_continente=`Qual o tempo de resposta do suporte da plataforma?_1`
table_Tempo_assistencia_tecnica_da_app_continente=table(Tempo_assistencia_tecnica_da_app_continente)
legendc6=c(attributes(table_Tempo_assistencia_tecnica_da_app_continente)$dimnames$Tempo_assistencia_tecnica_da_app_continente)# Legenda para o grafico
maths_func(Tempo_assistencia_tecnica_da_app_continente,app_continente,"tempo de assistencia")
graficos_barplot(Tempo_assistencia_tecnica_da_app_continente,table_Tempo_assistencia_tecnica_da_app_continente,app_continente,"tempo de assistencia")
grafico_pie(Tempo_assistencia_tecnica_da_app_continente,table_Tempo_assistencia_tecnica_da_app_continente,app_continente,"tempo de assistencia",legendc6)
grafico_plot(Tempo_assistencia_tecnica_da_app_continente,table_Tempo_assistencia_tecnica_da_app_continente,app_continente,"tempo de assistencia")

# ==========================================================
# 7->  Satisfação e frequencia
# =========================================================
tempo_frequencia_Continente=tirar_os_NAS(frequencia_de_uso_da_app_continente)

nota_do_site_Continente=tirar_os_NAS(Nota_da_app_continente)

satisfacaoFrequencia(nota_do_site_Continente,tempo_frequencia_Continente,app_continente)

# ==========================================================
# 8->  Problemas de usar o cartao
# =========================================================
Problemas_de_cartao_na_app_Continente=`Já teve problemas em associar o cartão bancário à plataforma?_1`
table_Problemas_de_cartao_na_app_Continente=table(Problemas_de_cartao_na_app_Continente)
contadorc8= count(Problemas_de_cartao_na_app_Continente) #conta
Problemas_de_cartao_na_app_Continente=na.omit(Problemas_de_cartao_na_app_Continente)
numero_respostas_totaisc8 = length(Problemas_de_cartao_na_app_Continente) #numero total de respostas

percentagem_respostasc8=round(contadorc8$freq/numero_respostas_totaisc8*100,2) # fazer a percentagens dos generos
percentagem_respostasc8=paste(percentagem_respostasc8,"%")

#criacao do grafico circular
pie(table_Problemas_de_cartao_na_app_Continente,main = str_c("Grafico circular de problemas em associar o cartao bancario da app ",app_continente),col = rainbow(length(table_Problemas_de_cartao_na_app_Continente)),labels=c(percentagem_respostasc8)) #grafico
legend( "topright",legend=c("Não","Sim"), fill = rainbow(length(table_Problemas_de_cartao_na_app_Continente)))

# ==========================================================
# boxplot
# =========================================================
nomes_continente=c("Frequencia","Recomendacao","Intuicao","Nota atribuida", "Tempo Uso","Tempo Assitencia")
titulo_pingo_doce="Boxplot dos da app Pingo doce"
grafico_boxplot(frequencia_de_uso_da_app_continente,Recomendacao_da_app_continente,Intuicao_da_app_continente,Nota_da_app_continente,Tempo_utilizacao_da_app_continente,Tempo_assistencia_tecnica_da_app_continente,nomes_continente,app_continente)



# ======================================================================================================================
# ======================================================================================================================
#                                                             Pingo Doce
# ======================================================================================================================
# ======================================================================================================================

app_pingo_doce="Pingo Doce"


# ====================================================
#    1-> FREQUENCIA DE USO DA PLATAFORMA Pingo Doce
# ====================================================
frequencia_de_uso_da_app_pingo_doce=`Com que frequência utiliza a plataforma?`
table_frequencia_de_uso_pingo_doce=table(frequencia_de_uso_da_app_pingo_doce)
maths_func(frequencia_de_uso_da_app_pingo_doce,app_pingo_doce,"frequencia")
graficos_barplot(frequencia_de_uso_da_app_continente,table_frequencia_de_uso_continente,app_pingo_doce,"Frequencia de Uso")

legendpd1=NULL
para_verifica=count(frequencia_de_uso_da_app_pingo_doce)$x
for (j in 1: length(para_verifica)) {
  if(para_verifica[j]==1){
    legendpd1[j] <- "Raramente"
  }else if(para_verifica[j]==2){
    legendpd1[j] <- "Mensalmente"
  }else if(para_verifica[j]==3){
    legendpd1[j] <- "Semanalmente"
  }else if(para_verifica[j]==4){
    legendpd1[j] <- "2/3 Semanalmente"
  }else if(para_verifica[j]==5){
    legendpd1[j] <- "Diariamente"
  }else{
    
  }
}
grafico_pie(frequencia_de_uso_da_app_pingo_doce,table_frequencia_de_uso_pingo_doce,app_pingo_doce,"Frequencia de Uso",legendpd1)
grafico_plot(frequencia_de_uso_da_app_continente,table_frequencia_de_uso_continente,app_pingo_doce,"Frequencia de Uso")
# =============================================
#       2-> RECOMENDACÃO DA APP PINGO DOCE
# =============================================
Recomendacao_da_app_Pingo_Doce= `Recomendaria a plataforma a familiares e amigos?`
table_Recomendacao_da_app_Pingo_Doce=table(Recomendacao_da_app_Pingo_Doce)
maths_func(Recomendacao_da_app_Pingo_Doce,app_pingo_doce,"recomendacao")
para_verifica=count(tirar_os_NAS(Recomendacao_da_app_Pingo_Doce))$x
legendpd2=VerificarClassifica(para_verifica)
graficos_barplot(Recomendacao_da_app_Pingo_Doce,table_Recomendacao_da_app_Pingo_Doce,app_pingo_doce,"recomendacao")
grafico_pie(Recomendacao_da_app_Pingo_Doce,table_Recomendacao_da_app_Pingo_Doce,app_pingo_doce,"recomendacao",legendpd2)
grafico_plot(Recomendacao_da_app_Pingo_Doce,table_Recomendacao_da_app_Pingo_Doce,app_pingo_doce,"recomendacao")
# =============================================
# 3-> Intuicao da app Pingo Doce
# =============================================
Intuicao_da_app_Pingo_Doce=`A plataforma(web/aplicação mobile) é intuitiva?`
table_Intuicao_da_app_Pingo_Doce=table(Intuicao_da_app_Pingo_Doce)
maths_func(Intuicao_da_app_Pingo_Doce,app_pingo_doce,"intuicao")
para_verifica=count(tirar_os_NAS(Intuicao_da_app_Pingo_Doce))$x
legendpd3=VerificarClassifica(para_verifica)
graficos_barplot(Intuicao_da_app_Pingo_Doce,table_Intuicao_da_app_Pingo_Doce,app_pingo_doce,"intuicao")
grafico_pie(Intuicao_da_app_Pingo_Doce,table_Intuicao_da_app_Pingo_Doce,app_pingo_doce,"intuicao",legendpd3)
grafico_plot(Intuicao_da_app_Pingo_Doce,table_Intuicao_da_app_Pingo_Doce,app_pingo_doce,"intuicao")
# =============================================
# 4-> Nota da app Pingo Doce
# =============================================
Nota_da_app_Pingo_Doce=`De 0 a 5, que nota atribui ao site?`
table_Nota_da_app_Pingo_Doce=table(Nota_da_app_Pingo_Doce)
maths_func(Nota_da_app_Pingo_Doce,app_pingo_doce,"nota da app")
para_verifica=count(tirar_os_NAS(Nota_da_app_Pingo_Doce))$x
legendpd4=VerificarClassifica(para_verifica)
graficos_barplot(Nota_da_app_Pingo_Doce,table_Nota_da_app_Pingo_Doce,app_pingo_doce,"nota da app")
grafico_pie(Nota_da_app_Pingo_Doce,table_Nota_da_app_Pingo_Doce,app_pingo_doce,"nota da app",legendpd4)
grafico_plot(Nota_da_app_Pingo_Doce,table_Nota_da_app_Pingo_Doce,app_pingo_doce,"nota da app")
# =============================================
# 5->  Tempo usando a plataforma Pingo Doce
# =============================================
Tempo_utilizacao_da_app_Pingo_Doce=`Quanto tempo passa usando a plataforma?`
table_Tempo_utilizacao_da_app_Pingo_Doce=table(Tempo_utilizacao_da_app_Pingo_Doce)
maths_func(Tempo_utilizacao_da_app_Pingo_Doce,app_pingo_doce,"tempo de utilizacao")
legendpd5=c(attributes(table_Tempo_utilizacao_da_app_Pingo_Doce)$dimnames$Tempo_utilizacao_da_app_Pingo_Doce)# Legenda para o grafico
graficos_barplot(Tempo_utilizacao_da_app_Pingo_Doce,table_Tempo_utilizacao_da_app_Pingo_Doce,app_pingo_doce,"tempo de utilizacao")
grafico_pie(Tempo_utilizacao_da_app_Pingo_Doce,table_Tempo_utilizacao_da_app_Pingo_Doce,app_pingo_doce,"tempo de utilizacao",legendpd5)
grafico_plot(Tempo_utilizacao_da_app_Pingo_Doce,table_Tempo_utilizacao_da_app_Pingo_Doce,app_pingo_doce,"tempo de utilizacao")
# =============================================
# 6->  Tempo de assistencia tecnica da app Pingo Doce
# =============================================
Tempo_assistencia_tecnica_da_app_Pingo_Doce=`Qual o tempo de resposta do suporte da plataforma?`
table_Tempo_assistencia_tecnica_da_app_Pingo_Doce=table(Tempo_assistencia_tecnica_da_app_Pingo_Doce)
maths_func(Tempo_assistencia_tecnica_da_app_Pingo_Doce,app_pingo_doce,"tempo de assistencia")
legendpg6=c(attributes(table_Tempo_assistencia_tecnica_da_app_Pingo_Doce)$dimnames$Tempo_assistencia_tecnica_da_app_Pingo_Doce)# Legenda para o grafico
graficos_barplot(Tempo_assistencia_tecnica_da_app_Pingo_Doce,table_Tempo_assistencia_tecnica_da_app_Pingo_Doce,app_pingo_doce,"tempo de assistencia")
grafico_pie(Tempo_assistencia_tecnica_da_app_Pingo_Doce,table_Tempo_assistencia_tecnica_da_app_Pingo_Doce,app_pingo_doce,"tempo de assistencia",legendpg6)
grafico_plot(Tempo_assistencia_tecnica_da_app_Pingo_Doce,table_Tempo_assistencia_tecnica_da_app_Pingo_Doce,app_pingo_doce,"tempo de assistencia")

# ==========================================================
# 7->  Satisfação e frequencia
# =========================================================
tempo_frequencia_Pingo_Doce=tirar_os_NAS(frequencia_de_uso_da_app_pingo_doce)

nota_do_site_Pingo_Doce=tirar_os_NAS(Nota_da_app_Pingo_Doce)

satisfacaoFrequencia(nota_do_site_Pingo_Doce,tempo_frequencia_Pingo_Doce,app_pingo_doce)

# ==========================================================
# 8->  Problemas de usar o cartao
# =========================================================
Problemas_de_cartao_na_app_Pingo_Doce=`Já teve problemas em associar o cartão bancário à plataforma?`
table_Problemas_de_cartao_na_app_Pingo_Doce=table(Problemas_de_cartao_na_app_Pingo_Doce)
contadorpd8= count(table_Problemas_de_cartao_na_app_Pingo_Doce) #conta
Problemas_de_cartao_na_app_Pingo_Doce=na.omit(Problemas_de_cartao_na_app_Pingo_Doce)
numero_respostas_totaispd8 = length(Problemas_de_cartao_na_app_Continente) #numero total de respostas

percentagem_respostaspd8=round(contadorpd8$freq/numero_respostas_totaispd8*100,2) # fazer a percentagens dos generos
percentagem_respostaspd8=paste(percentagem_respostaspd8,"%")

#criacao do grafico circular
pie(table_Problemas_de_cartao_na_app_Pingo_Doce,main = str_c("Grafico circular de problemas em associar o cartao bancario da app ",app_pingo_doce),col = rainbow(length(table_Problemas_de_cartao_na_app_Pingo_Doce)),labels=c(percentagem_respostasc8)) #grafico
legend( "topright",legend=c("Não","Sim"), fill = rainbow(length(table_Problemas_de_cartao_na_app_Pingo_Doce)))

# ==========================================================
# boxplot
# =========================================================
nomes_pingo_doce=c("Frequencia","Recomendacao","Intuicao","Nota atribuida", "Tempo Uso","Tempo Assitencia")
titulo_pingo_doce="Boxplot dos da app Pingo doce"
grafico_boxplot(frequencia_de_uso_da_app_pingo_doce,Recomendacao_da_app_Pingo_Doce,Intuicao_da_app_Pingo_Doce,Nota_da_app_Pingo_Doce,Tempo_utilizacao_da_app_Pingo_Doce,Tempo_assistencia_tecnica_da_app_Pingo_Doce,nomes_pingo_doce,app_pingo_doce)




# TESTES
dataframeFreqUso=data.frame(frequencia_de_uso_da_app_pingo_doce,frequencia_de_uso_da_app_continente)
dataframeAvaliacao=data.frame(Nota_da_app_Pingo_Doce,Nota_da_app_continente)

dataframeFreqUso=dataframeFreqUso[complete.cases(dataframeFreqUso), ]
dataframeFreqUso=strtoi(dataframeFreqUso, base = 0L)
dataframeAvaliacao=dataframeAvaliacao[complete.cases(dataframeAvaliacao), ]
dataframeAvaliacao=strtoi(dataframeAvaliacao, base = 0L)
## para a realização da correlacão,a nossa amostra resumiu-se a 8 respostas,respostas essas que usavam as duas plataformas em simultaneo  
#correlação entre Frequencia de uso e avaliação das duas Plataformas em siultaneo
testeCorrelacaoSimultaneo<-cor.test(dataframeFreqUso ,dataframeAvaliacao , alternative ="two.sided", method ="kendall")
testeCorrelacaoSimultaneo



#correlação entre Frequencia de uso e avaliaçãoContinente 
testeCorrelacaoPingoDoce<-cor.test(frequencia_de_uso_da_app_continente ,Nota_da_app_continente , alternative ="two.sided", method ="kendall")
testeCorrelacaoPingoDoce



#correlação entre Frequencia de uso e avaliação  Pingo Doce 
testeCorrelacaoPingoDoce<-cor.test(frequencia_de_uso_da_app_pingo_doce ,Nota_da_app_Pingo_Doce , alternative ="two.sided", method ="kendall")
testeCorrelacaoPingoDoce




#comparação
# frequencia uso plataformas Pingo-Doce e Contiente 



#hipotese nula (H0): Plataforma continente Melhor 
#hipote alternativa (H1): Plataforma Pingo-Doce Melhor 
#nivel de significancia = 5%
resTest1<-t.test (frequencia_de_uso_da_app_continente,frequencia_de_uso_da_app_pingo_doce,  alternative ="less",conf.level = 0.95,paired = TRUE)
resTest1



# avalaiacao plataformas Pingo Doce e contiente 



#hipotese nula (H0): Plataforma continente Melhor 
#hipote alternativa (H1): Plataforma Pingo-Doce Melhor 
#nivel de significancia = 5%



resTest2<-t.test (Nota_da_app_continente,Nota_da_app_Pingo_Doce, alternative ="less",conf.level = 0.95,paired = TRUE)
resTest2