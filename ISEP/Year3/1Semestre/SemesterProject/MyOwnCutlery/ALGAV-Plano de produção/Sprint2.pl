% F?BRICA
 
% Linhas
 
linhas([lA]).
 
 
% Maquinas
 
 
maquinas([ma,mb,mc,md,me]).
 
 
 
% Ferramentas
 
 
ferramentas([fa,fb,fc,fd,fe,ff,fg,fh,fi,fj]).
 
 
% Maquinas que constituem as Linhas
 
tipos_maq_linha(lA,[ma,mb,mc,md,me]).
% ...
 
 
% Opera??es
 
tipo_operacoes([opt1,opt2,opt3,opt4,opt5,opt6,opt7,opt8,opt9,opt10]).
 
% operacoes/1 vai ser criado dinamicamente
%no exemplo dara' uma lista com 30 operacoes 6 lotes de produtos * 5 operacoes por produto
 
%operacoes_atrib_maq/2 vai ser criado dinamicamente
%no exemplo cada maquina tera' 6 operacoes atribuidas, uma por cada lote de produtos
 
% classif_operacoes/2 deve ser criado dinamicamente
%no exemplo teremos 30 factos deste tipo, um para cada operacao
 
 
% Afeta??o de tipos de opera??es a tipos de m?quinas
% com ferramentas, tempos de setup e tempos de execucao)
 
operacao_maquina(opt1,ma,fa,1,1).
operacao_maquina(opt2,mb,fb,2.5,2).
operacao_maquina(opt3,mc,fc,1,3).
operacao_maquina(opt4,md,fd,1,1).
operacao_maquina(opt5,me,fe,2,3).
operacao_maquina(opt6,mb,ff,1,4).
operacao_maquina(opt7,md,fg,2,5).
operacao_maquina(opt8,ma,fh,1,6).
operacao_maquina(opt9,me,fi,1,7).
operacao_maquina(opt10,mc,fj,20,2).
%...
 
 
% PRODUTOS
 
produtos([pA,pB,pC,pD,pE,pF]).
 
operacoes_produto(pA,[opt1,opt2,opt3,opt4,opt5]).
operacoes_produto(pB,[opt1,opt6,opt3,opt4,opt5]).
operacoes_produto(pC,[opt1,opt2,opt3,opt7,opt5]).
operacoes_produto(pD,[opt8,opt2,opt3,opt4,opt5]).
operacoes_produto(pE,[opt1,opt2,opt3,opt4,opt9]).
operacoes_produto(pF,[opt1,opt2,opt10,opt4,opt5]).
 
 
 
% ENCOMENDAS
 
%Clientes
 
clientes([clA,clB,clC]).
 
 
% prioridades dos clientes
 
prioridade_cliente(clA,2).
prioridade_cliente(clB,1).
prioridade_cliente(clB,3).
 
% ...
 
% Encomendas do cliente,
% termos e(<produto>,<n.unidades>,<tempo_conclusao>)
 
encomenda(clA,[e(pA,4,50),e(pB,4,70)]).
encomenda(clB,[e(pC,3,30),e(pD,5,200)]).
encomenda(clC,[e(pE,4,60),e(pF,6,120)]).
% ...
 
 
 
 
% cria_op_enc - fizeram-se correcoes face a versao anterior
 
:- dynamic operacoes_atrib_maq/2.
:- dynamic classif_operacoes/2.
:- dynamic op_prod_client/9.
:- dynamic operacoes/1.
 
 
cria_op_enc:-retractall(operacoes(_)),
retractall(operacoes_atrib_maq(_,_)),retractall(classif_operacoes(_,_)),
retractall(op_prod_client(_,_,_,_,_,_,_,_,_)),
        findall(t(Cliente,Prod,Qt,TConc),
        (encomenda(Cliente,LE),member(e(Prod,Qt,TConc),LE)),
        LT),cria_ops(LT,0),
findall(Op,classif_operacoes(Op,_),LOp),asserta(operacoes(LOp)),
maquinas(LM),
 findall(_,
        (member(M,LM),
         findall(Opx,op_prod_client(Opx,M,_,_,_,_,_,_,_),LOpx),
         assertz(operacoes_atrib_maq(M,LOpx))),_).
 
cria_ops([],_).
cria_ops([t(Cliente,Prod,Qt,TConc)|LT],N):-
            operacoes_produto(Prod,LOpt),
    cria_ops_prod_cliente(LOpt,Cliente,Prod,Qt,TConc,N,N1),
            cria_ops(LT,N1).
 
 
cria_ops_prod_cliente([],_,_,_,_,Nf,Nf).
cria_ops_prod_cliente([Opt|LOpt],Client,Prod,Qt,TConc,N,Nf):-
        cria_ops_prod_cliente2(Opt,Prod,Client,Qt,TConc,N,Ni),
    cria_ops_prod_cliente(LOpt,Client,Prod,Qt,TConc,Ni,Nf).
 
 
cria_ops_prod_cliente2(Opt,Prod,Client,Qt,TConc,N,Ni):-
            Ni is N+1,
            atomic_concat(op,Ni,Op),
            assertz(classif_operacoes(Op,Opt)),
            operacao_maquina(Opt,M,F,Tsetup,Texec),
    assertz(op_prod_client(Op,M,F,Prod,Client,Qt,TConc,Tsetup,Texec)).
 
 
 
:-cria_op_enc.

%%------------------------------------------------------------EXERCICIO 1-------------------------------------------------------------
%1.1-gerar sequenciamentos de opera??es para 1 m?quina considerando tempos de setup (prepara??o da m?quina com a ferramenta necess?ria) e tentar encontrar o melhor sequenciamento segundo o crit?rio de menor tempo de execu??o
%Dado uma m?quina retorna uma lista de opera??es e o tempo de ocupa??o
%%parametros:
%           - M:maquina
%           -L lista Opera??es da maquina 
%           -T lista resultatante

ocupacao(M,L,T):-
    get_time(Ti),
    ordenaOperacoesPorFerramenta(M,L),
    tempoOcupacao(semfer,L,T),
    get_time(Tf), Tcomp is Tf-Ti,
    write('Gerado em '), write(Tcomp),
    write(' segundos'),nl.
 
% Cria pares Ferramenta-Operacao
parOperacaoFerramenta(OP,FE-OP):-op_prod_client(OP,_,FE,_,_,_,_,_,_).
% Cria uma lista de operacoes ordenada alfabeticamente pelas ferramentas das opera??es
% M- Maquina
% LO- lista de operaceos da maquina
% SLK- lista ordenada pelas keys
% S- lista resultante ordenada 
% PL- lista de pares operacoes ferramentas 
ordenaOperacoesPorFerramenta(M,S):- operacoes_atrib_maq(M,LO), maplist(parOperacaoFerramenta,LO,PL), keysort(PL,SLK), pairs_values(SLK,S).
% Calcula tempo de ocupa??o de uma lista de opera??es
% F- ferramenta
% Lista no segundo parametro: lista que contem as opera??es ordenandas pela ferramenta
% T- variavel que ir? conter o somat?rio do tempo de ocupa??o das opera??es
tempoOcupacao(_,[],0).
tempoOcupacao(F,[H|L],T):- op_prod_client(H,_,F1,_,_,_,_,Tset,Texec), tempoOcupacao(F1,L,T1),
                            ((F1==F,!,T is Texec+T1);T is Tset+Texec+T1).
 
% 1.2-Dado uma m?quina retorna uma lista de opera??es e a soma dos tempos de atraso
atraso(M,L,T):-
    get_time(Ti),
    ordenaOperacoesPorPrazo(M,L),
    reverse(L,S),
    tempoAtraso(semfer,S,T,0,0),
    get_time(Tf), Tcomp is Tf-Ti,
    write('Gerado em '), write(Tcomp),
    write(' segundos'),nl.
 
% Cria pares Prazo-Operacao
parPrazoOperacao(O,P-O):-op_prod_client(O,_,_,_,_,_,P,_,_).
% Cria uma lista de operacoes ordenada, ascendente, pelos prazos
% M-maquina
% S- lista contendo as opera??es ordenadas pelo prazo
% Y- lista ordenada pelas keys
% L- lista de operaceos da maquina
% X- lista de pares operacoes prazo 
ordenaOperacoesPorPrazo(M,S):- operacoes_atrib_maq(M,L), maplist(parPrazoOperacao,L,X), keysort(X,Y), pairs_values(Y,S).
% Calcula soma dos tempos de atraso de uma lista de opera??es
% % F- ferramenta
% Lista no segundo parametro: lista que contem as opera??es ordenandas pelo prazo
% T- variavel que ir? conter o somat?rio do tempo de atraso das opera??es
% Tocupacao- variavel que guarsa o tempo de ocupa??o
% Tatraso- variavel que guarsa o tempo de atrasp
tempoAtraso(_,[],T,_,Tatraso):- T is Tatraso,!.
tempoAtraso(F,[O|L],T,Tocupacao,Tatraso):- op_prod_client(O,_,F1,_,_,_,Prazo,Tset,Texec),
                            ((F==F1,!,To1 is Tocupacao + Texec);To1 is Tocupacao+Texec+Tset),
                            ((To1<Prazo,!,Ta1 is Tatraso);Ta1 is To1-Prazo+Tatraso),
                            tempoAtraso(F1,L,T,To1,Ta1).
 
%----------------------------------------------------EXERCICIO 2-------------------------------------------------------------

% melhor escalonamento com findall, gera todas as solucoes e escolhe melhor

% ------------------------------------------------TEMPO OCUPA??O-----------------------------------------------------------
:- dynamic melhor_sol_to/2.
melhor_escalonamentoComTempoOcupacaoComFindAll(M,Lm,Tm):-
                get_time(Ti),
                findall(p(LP,Tempo),
                permuta_tempo(M,LP,Tempo), LL),
                melhor_permuta(LL,Lm,Tm),
                get_time(Tf),Tcomp is Tf-Ti,
                write('GERADO EM '),write(Tcomp),
                write(' SEGUNDOS'),nl.

melhor_permuta([p(LP,Tempo)],LP,Tempo):-!.
melhor_permuta([p(LP,Tempo)|LL],LPm,Tm):-
         melhor_permuta(LL,LP1,T1),
        ((Tempo<T1,!,Tm is Tempo,LPm=LP);(Tm is T1,LPm=LP1)).

melhor_escalonamentoComTempoOcupacaoSemFindAll(M,Lm,Tm):-
        get_time(Ti),
        (melhor_escalonamentoOcupacao(M);true),retract(melhor_sol_to(Lm,Tm)),
        get_time(Tf),Tcomp is Tf-Ti,
        write('GERADO EM '),write(Tcomp),
        write(' SEGUNDOS'),nl.

melhor_escalonamentoOcupacao(M):- asserta(melhor_sol_to(_,10000)),!,
        permuta_tempoComTempoOcupacao(M,LP,Tempo),
        atualiza(LP,Tempo),
        fail.

permuta_tempoComTempoOcupacao(M,LP,Tempo):- 
            operacoes_atrib_maq(M,L),
            permuta(L,LP),
            tempoOcupacao(semfer,LP,Tempo).

atualiza(LP,T):-melhor_sol_to(_,Tm),T<Tm,retract(melhor_sol_to(_,_)),asserta(melhor_sol_to(LP,T)),!. 

%----------------------------------------------TEMPO ATRASO--------------------------------------------------------%
melhor_escalonamentoComTempoAtrasoComFindAll(M,Lm,Tm):-
                get_time(Ti),
                findall(p(LP,Tempo), 
                permuta_tempoComTempoAtraso(M,LP,Tempo), LL),
                melhor_permuta(LL,Lm,Tm),
                get_time(Tf),Tcomp is Tf-Ti,
                write('GERADO EM '),write(Tcomp),
                write(' SEGUNDOS'),nl.

melhor_escalonamentoComTempoAtrasoSemFindAll(M,Lm,Tm):-
        get_time(Ti),
        (melhor_escalonamentoAtraso(M);true),retract(melhor_sol_to(Lm,Tm)),
        get_time(Tf),Tcomp is Tf-Ti,
        write('GERADO EM '),write(Tcomp),
        write(' SEGUNDOS'),nl.

permuta_tempoComTempoAtraso(M,LP,Tempo):- 
        operacoes_atrib_maq(M,L),
        permuta(L,LP),
        tempoAtraso(semfer,LP,Tempo,0,0).

melhor_escalonamentoAtraso(M):- 
        asserta(melhor_sol_to(_,10000)),!,
        permuta_tempoComTempoAtraso(M,LP,Tempo),
        atualiza(LP,Tempo),
        fail.

%------------------------------------------------- Sprint 1 -----------------------------------------------------
estimativa(LOp,Estimativa):-
    findall(p(FOp,Tsetup),
    (member(Op,LOp),op_prod_client(Op,_,FOp,_,_,_,_,Tsetup,_)),
    LFTsetup),
    elimina_repetidos(LFTsetup,L),
    soma_setups(L,Estimativa).

elimina_repetidos([],[]).
elimina_repetidos([X|L],L1):-member(X,L),!,elimina_repetidos(L,L1).
elimina_repetidos([X|L],[X|L1]):-elimina_repetidos(L,L1).
soma_setups([],0).
soma_setups([p(_,Tsetup)|L],Ttotal):- soma_setups(L,T1), Ttotal is Tsetup+T1.
				
obterTemposPorOperacao(Op1,M,F,Ts,Te):-
	classif_operacoes(Op1,Opt1), %obtem o tipo de operacao conforme a operacao
	operacao_maquina(Opt1,M,F,Ts,Te). % obtem e retorna nas variaveis Ts maquina e ferramenta	

verificarFerramenta(M,F,[Op2|ROperacoes]):-	
	obterTemposPorOperacao(Op2,_,F,_,_). % verifica se a ferramenta F e igual a ferramenta da Op2 

estimativaS(LOp,Estimativa,CustoAtual):-
	findall(p(Op,Atraso),
	(member(Op,LOp),op_prod_client(Op,M,F,_,_,_,To,Ts,Te),
	((verificarFerramenta(M,F,LOp),!,CX is Te);CX is Ts+Te),
	Aux2 is CustoAtual + CX,
	Atraso is Aux2 - To ),
	LFTsetup),
	soma_atrasos(LFTsetup,Estimativa).

soma_atrasos([],0).
soma_atrasos([p(_,Atraso)|L],Ttotal):- soma_atrasos(L,T1), Ttotal is Atraso+T1.

%---------------------------------------Sprint 2--------------------------------------------------

:-dynamic geracoes/1.
:-dynamic populacao/1.
:-dynamic prob_cruzamento/1.
:-dynamic prob_mutacao/1.


% tarefa(Id,TempoProcessamento,TempConc,PesoPenalizacao).
tarefa(t1,2,5,1).
tarefa(t2,4,7,6).
tarefa(t3,1,11,2).
tarefa(t4,3,9,3).
tarefa(t5,3,8,2).

% tarefas(NTarefas).
tarefas(5).

% parameteriza��o
inicializa:-write('Numero de novas Geracoes: '),read(NG), 			(retract(geracoes(_));true), asserta(geracoes(NG)),
	write('Dimensao da Populacao: '),read(DP),
	(retract(populacao(_));true), asserta(populacao(DP)),
	write('Probabilidade de Cruzamento (%):'), read(P1),
	PC is P1/100, 
	(retract(prob_cruzamento(_));true), 	asserta(prob_cruzamento(PC)),
	write('Probabilidade de Mutacao (%):'), read(P2),
	PM is P2/100, 
	(retract(prob_mutacao(_));true), asserta(prob_mutacao(PM)).


gera:-
	inicializa,
	gera_populacao(Pop),
	write('Pop='),write(Pop),nl,
	avalia_populacao(Pop,PopAv),
	write('PopAv='),write(PopAv),nl,
	ordena_populacao(PopAv,PopOrd),
	geracoes(NG),
	gera_geracao(0,NG,PopOrd).

gera_populacao(Pop):-
	populacao(TamPop),	
        TamPop1 is TamPop - 2
	tarefas(NumT),
	findall(Tarefa,tarefa(Tarefa,_,_,_),ListaTarefas),
	gera_populacao(TamPop1 ,ListaTarefas,NumT,Pop).
	

gera_populacao(0,_,_,[Lm,LT]):- menorAtrasoEncomendaComPrioridades_SemShortestpath(Lm,T),menorAtrasoEncomendaComPrioridades_Shortestpath(LT,X).

gera_populacao(TamPop,ListaTarefas,NumT,[Ind|Resto]):-
	TamPop1 is TamPop-1,
	gera_populacao(TamPop1,ListaTarefas,NumT,Resto),
	gera_individuo(ListaTarefas,NumT,Ind), 
	not(member(Ind,Resto)).
gera_populacao(TamPop,ListaTarefas,NumT,L):-
	gera_populacao(TamPop,ListaTarefas,NumT,L).

gera_individuo([G],1,[G]):-!.

gera_individuo(ListaTarefas,NumT,[G|Resto]):-
	NumTemp is NumT + 1, % To use with random
	random(1,NumTemp,N),
	retira(N,ListaTarefas,G,NovaLista),
	NumT1 is NumT-1,
	gera_individuo(NovaLista,NumT1,Resto).

retira(1,[G|Resto],G,Resto).
retira(N,[G1|Resto],G,[G1|Resto1]):-
	N1 is N-1,
	retira(N1,Resto,G,Resto1).

avalia_populacao([],[]).
avalia_populacao([Ind|Resto],[Ind*V|Resto1]):-
	avalia(Ind,V),
	avalia_populacao(Resto,Resto1).

avalia(Seq,V):-
	avalia(Seq,0,V).

avalia([],_,0).
avalia([T|Resto],Inst,V):-
	tarefa(T,Dur,Prazo,Pen),
	InstFim is Inst+Dur,
	avalia(Resto,InstFim,VResto),
	(
		(InstFim =< Prazo,!, VT is 0)
  ;
		(VT is (InstFim-Prazo)*Pen)
	),
	V is VT+VResto.

ordena_populacao(PopAv,PopAvOrd):-
	bsort(PopAv,PopAvOrd).

bsort([X],[X]):-!.
bsort([X|Xs],Ys):-
	bsort(Xs,Zs),
	btroca([X|Zs],Ys).


btroca([X],[X]):-!.

btroca([X*VX,Y*VY|L1],[Y*VY|L2]):-
	VX>VY,!,
	btroca([X*VX|L1],L2).

btroca([X|L1],[X|L2]):-btroca(L1,L2).


gera_geracao(G,G,Pop):-!,
	write('Gera��o '), write(G), write(':'), nl, write(Pop), nl.

gera_geracao(N,G,Pop):-
	write('Gera��o '), write(N), write(':'), nl, write(Pop), nl,
	random_permutation(Pop,Ne),
	cruzamento(Ne,NPop1),
	mutacao(NPop1,NPop),
	avalia_populacao(NPop,NPopAv),
	ordena_populacao(NPopAv,NPopOrd),
	N1 is N+1,
	gera_geracao(N1,G,NPopOrd).

%o criterio de paragem adotado foi quando atingir o determinado valor.
gera_geracao(N,G,Pop,ValorCriterio,ValorIncremental):-
	write('Geracao),write(N),write(':'), nl, write(Pop), nl,
	random_permutation(Pop,NovaReordenacao), %reordena a lista das populacao para preparar para um solu�ao mais random
	cruzamento(NovaReordenacao,NPop1), %o cruzamento e realizado de modo random dado a lista ter sido permutada
	mutacao(NPop1,NPop2), %realiza o cruzamento
	avalia_populacao(NPop2,NPopAv),	%as avalicoes da lista populacoes encontra-se no segundo argumento
	append(NPopAv,NovaReordenacao,GeracaoNovaComAntiga), %juntamos as duas gera��es de seguida removemos repetidos e escolhemos os 2 melhores
	eliminaRepetidos(GeracaoNovaComAntiga,GeracaoTotalSemRep) %remove todas as repeticoes, nunca tera menos que os dados necessarios porque a 				geracao anterior ja tinha validado que nao continha repetidos
	obterMin(GeracaoTotalSemRep,Max1), %obtem o minimo da lista por outras palavras a melhor solucao ate ao momento
	indexof(Index1,GeracaoTotalSemRep,Max1), %obtem o index que contem o valor Max
	eliminaNoIndex(Index1,GeracaoTotalSemRep,GeracaoTotalSemRep1),	%elimina o valor para nao contar nos torneios conta a partir de 1 nao 0
	obterMin(GeracaoTotalSemRep,Max2), %obtem o minimo da lista por outras palavras a segunda melhor solucao ate ao momento
	indexof(Index2,GeracaoTotalSemRep1,Max2), 
	eliminaNoIndex(Index2,GeracaoTotalSemRep1,GeracaoSemMax),	%elimina o valor para nao contar nos torneios	
	append([Max1],[Max2],ListaAntesTorneio), %adiciona os dois melhores ja extraidos
	random_permutation(GeracaoSemMax,GeracaoSemMaxReordenada), %reordena a lista
	length(GeracaoSemMaxReordenada,Len),
	(Len==6,
		append(ListaAntesTorneio,GeracaoSemMaxReordenada,NovaGeracao); %dado que 2+6 =8 nao e necessario fazer torneio
		(Len>12,
			(
				torneioMenosDobro(GeracaoSemMaxReordenada,_,_,RetornoTorneio),
				append(ListaAntesTorneio,RetornoTorneio,NovaGeracao) %adiciona os dois melhores ao resultado dos torneios
			);
		(Len==12,
			(
				torneioDobro(GeracaoSemMaxReordenada,_,_,RetornoTorneio),
				append(ListaAntesTorneio,RetornoTorneio,NovaGeracao) %adiciona os dois melhores ao resultado dos torneios
			);
			(
				torneioMaisDobro(GeracaoSemMaxReordenada,_,_,RetornoTorneio),
				append(ListaAntesTorneio,RetornoTorneio,NovaGeracao), %adiciona os dois melhores ao resultado dos torneios
			)
		)
	), %conforme os dados que tem faz diferentes torneios
	
	ordena_populacao(NovaGeracao,NovaGeracaoOrdenada),
	ValorIncremental1 is ValorIncremental+1,
	(ValorIncremental1 == ValorCriterio,!;gera_geracao(N,G,Pop,ValorCriterio,ValorIncremental1)).
		

torneioGenerico(ListaInicial,ListaAposTorneio):-
	length(ListaInicial,Len), (Len==6, ListaAposTorneio = ListaInicial; 
                              (Len>12, torneioMaisDobro(ListaInicial,_,ListaAposTorneio);
                              		torneioMenosDobro(ListaInicial,ListaAposTorneio))).

	
eliminaNoIndex(1,[_|T],T).
eliminaNoIndex(P,[X|Y],[X|R]):-
 	P1 is P-1,
 	eliminaNoIndex(P1,Y,R).

eliminaRepetidos([],_):-!.
eliminaRepetidos([H | T], List) :-    
     member(H, T),
     eliminaRepetidos( T, List).

eliminaRepetidos([H | T], [H|T1]) :- 
      \+member(H, T),
      eliminaRepetidos( T, T1).


gerar_pontos_cruzamento(P1,P2):-
	gerar_pontos_cruzamento1(P1,P2).

gerar_pontos_cruzamento1(P1,P2):-
	tarefas(N),
	NTemp is N+1,
	random(1,NTemp,P11),
	random(1,NTemp,P21),
	P11\==P21,!,
	((P11<P21,!,P1=P11,P2=P21);(P1=P21,P2=P11)).
gerar_pontos_cruzamento1(P1,P2):-
	gerar_pontos_cruzamento1(P1,P2).



cruzamento([],[]).
cruzamento([Ind*_],[Ind]).
cruzamento([Ind1*_,Ind2*_|Resto],[NInd1,NInd2|Resto1]):-
	gerar_pontos_cruzamento(P1,P2),
	prob_cruzamento(Pcruz),random(0.0,1.0,Pc),
	((Pc =< Pcruz,!,
        cruzar(Ind1,Ind2,P1,P2,NInd1),
	  cruzar(Ind2,Ind1,P1,P2,NInd2))
	;
	(NInd1=Ind1,NInd2=Ind2)),
	cruzamento(Resto,Resto1).

preencheh([],[]).

preencheh([_|R1],[h|R2]):-
	preencheh(R1,R2).


sublista(L1,I1,I2,L):-
	I1 < I2,!,
	sublista1(L1,I1,I2,L).

sublista(L1,I1,I2,L):-
	sublista1(L1,I2,I1,L).

sublista1([X|R1],1,1,[X|H]):-!,
	preencheh(R1,H).

sublista1([X|R1],1,N2,[X|R2]):-!,
	N3 is N2 - 1,
	sublista1(R1,1,N3,R2).

sublista1([_|R1],N1,N2,[h|R2]):-
	N3 is N1 - 1,
	N4 is N2 - 1,
	sublista1(R1,N3,N4,R2).

rotate_right(L,K,L1):-
	tarefas(N),
	T is N - K,
	rr(T,L,L1).

rr(0,L,L):-!.

rr(N,[X|R],R2):-
	N1 is N - 1,
	append(R,[X],R1),
	rr(N1,R1,R2).


elimina([],_,[]):-!.

elimina([X|R1],L,[X|R2]):-
	not(member(X,L)),!,
	elimina(R1,L,R2).

elimina([_|R1],L,R2):-
	elimina(R1,L,R2).

insere([],L,_,L):-!.
insere([X|R],L,N,L2):-
	tarefas(T),
	((N>T,!,N1 is N mod T);N1 = N),
	insere1(X,N1,L,L1),
	N2 is N + 1,
	insere(R,L1,N2,L2).


insere1(X,1,L,[X|L]):-!.
insere1(X,N,[Y|L],[Y|L1]):-
	N1 is N-1,
	insere1(X,N1,L,L1).

cruzar(Ind1,Ind2,P1,P2,NInd11):-
	sublista(Ind1,P1,P2,Sub1),
	tarefas(NumT),
	R is NumT-P2,
	rotate_right(Ind2,R,Ind21),
	elimina(Ind21,Sub1,Sub2),
	P3 is P2 + 1,
	insere(Sub2,Sub1,P3,NInd1),
	eliminah(NInd1,NInd11).


eliminah([],[]).

eliminah([h|R1],R2):-!,
	eliminah(R1,R2).

eliminah([X|R1],[X|R2]):-
	eliminah(R1,R2).

mutacao([],[]).
mutacao([Ind|Rest],[NInd|Rest1]):-
	prob_mutacao(Pmut),
	random(0.0,1.0,Pm),
	((Pm < Pmut,!,mutacao1(Ind,NInd));NInd = Ind),
	mutacao(Rest,Rest1).

mutacao1(Ind,NInd):-
	gerar_pontos_cruzamento(P1,P2),
	mutacao22(Ind,P1,P2,NInd).

mutacao22([G1|Ind],1,P2,[G2|NInd]):-
	!, P21 is P2-1,
	mutacao23(G1,P21,Ind,G2,NInd).
mutacao22([G|Ind],P1,P2,[G|NInd]):-
	P11 is P1-1, P21 is P2-1,
	mutacao22(Ind,P11,P21,NInd).

mutacao23(G1,1,[G2|Ind],G2,[G1|Ind]):-!.
mutacao23(G1,P,[G|Ind],G2,[G|NInd]):-
	P1 is P-1,
	mutacao23(G1,P1,Ind,G2,NInd).

%this method sort a list and return the value of the list
edd(L,LOrd,Val) 
	ordena_populacao(L,LOrd,),
	avalia(LOrd,Val).




%a lista foi permutada para nao ser
% sempre a mesma ordem isto e quem estava no fim por comparar com o segundo pois este pode passar para inicio
torneioMaisDobro([],_,_,_):-!.
torneioMaisDobro([H|Lista],Random,Res,RestornoTorneio):-
	
	obtemValorParaLista(H,V),
	append([V],Random,Random),
    append([H],Res,Res),
	torneioMaisDobro(Lista,Random,Res,RestornoTorneio),
    eliminaAteDobro(Random,Res,DobroRandom,DobroRes),
    torneioDobro(DobroRandom,DobroRes,RestornoTorneio).

torneioDobro(_,[],_):-!.
torneioDobro([H|T],ListaRandom,ListaValor,RestornoTorneio):-
	obtemValorParaLista(H,V),
	append([H],ListaValor,ListaValor),
	append([V],ListaRandom,ListaRandom),
	torneioDobro(T,ListaRandom,ListaValor,RestornoTorneio),
	torneioDobro2(ListaRandom,ListaValor,RestornoTorneio),

    append([Val],RestornoTorneio,RestornoTorneio)
    torneioDobro(DobroRandom,DobroRes,RestornoTorneio).

torneioDobro2([],[],_):-!.
torneioDobro2(ListaRandom,ListaValor,RestornoTorneio):-
	comp(ListaRandom,ListaValor,Valor),
	append([Valor],RestornoTorneio,RestornoTorneio1),
	eliminaNoIndex(1,ListaRandom,ListaRandom1),
	eliminaNoIndex(2,ListaRandom1,ListaRandom2),
	eliminaNoIndex(1,ListaValor,ListaValor1),
	eliminaNoIndex(1,ListaValor1,ListaValor2),
	torneioDobro2(ListaRandom,ListaValor,RestornoTorneio1).

comp([HRandom|ListaRandom],[HValor|ListaValor],Valor):-
	conf(ListaRandom,ListaValor,HRandom,HValor,Valor).

conf([HRandom|ListaRandom],[HValor|ListaValor],HHRandom,HHValor,Valor):-
	(HRandom<HHRandom, Valor is HValor;Valor is HHValor).
	
eliminaAteDobro(Random,Res,DobroRandom,DobroRes):-
    length(Res,Len), (   Len==13, eliminaRandom1(Random,Res,DobroRandom,DobroRes);
                                 eliminaRandom2(Random,Res,DobroRandom,DobroRes) ).

eliminaRandom1(Random,Res,DobroRandom,DobroRes):-
    obterMax(Random,X),
    indexof(A,X,Random),
    delete(Random,X,DobroRandom),
    nth1(A,Res,Z),
    delete(Res,Z,DobroRes).
    

eliminaRandom2(Random,Res,DobroRandom,DobroRes):-
    eliminaRandom1(Random,Res,X,Y),
    eliminaRandom1(X,Y,DobroRandom,DobroRes).
  

obtemValorParaLista(H,V):-
	avalia(H,Val1),
	random(0,Val1,V).

obterMax([X|Xs], R):- obterMax(Xs, X, R). 
obterMax([], R, R).
obterMax([X|Xs], Y, R):- X >  Y, obterMax(Xs, X, R).
obterMax([X|Xs], Y, R):- X =< Y, obterMax(Xs, Y, R).

obterMin([X|Xs], R):- obterMin(Xs, X, R). 
obterMin([], R, R).
obterMin([X|Xs], Y, R):- X =<  Y, obterMin(Xs, X, R).
obterMin([X|Xs], Y, R):- X > Y, obterMin(Xs, Y, R).

indexof(Index, Item, List):-
  nth1(Index, List, Item).
indexof(-1, _, _).

torneioMenosDobro2(Random,Res,RestornoTorneio):-
  	   length(Res,Len),
   	   (Len>=2,
       nth1(1,Random,Var1),
       nth1(2,Random,Var2),
       append(Var1,Var2,Lista3),
       nth1(1,Res,Var3),
       nth1(2,Res,Var4),
       append(Var3,Var4,Lista4),
       dividirListas(Lista3,Lista4,ListaMaior,ListaMenor,RestornoTorneio,ListaMenorNova),
       torneioMenosDobro()  
       
    )

    dividirListas(Lista3,Lista4,ListaMaior,ListaMenor,RestornoTorneio,ListaMenorNova,ListaResMenor):-
    obterMax(Lista3,X),
    nth1(1,Lista3,Valor1),
    nth1(2,Lista3,Valor2),
    nth1(1,Lista4,Valor3),
    nth1(2,Lista4,Valor4),
    ( Valor1==X,(append(ListaMaior,[X],RestornoTorneio),append(ListaMenor,[Valor2],ListaMenorNova),delete(Lista4,Valor3,ListaResMenor2),append());
    (append(ListaMaior,[Valor2],RestornoTorneio),append(ListaMenor,[Valor1],ListaMenorNova))).








%Criar as tarefas dinamicamente 
% Notas:
	   %produto corresponde a uma tarefa 
:-dynamic produtos/1.
:-dynamic tarefa/4.
:-dynamic tarefas/1.

cria_Tarefas:-
    retractall(tarefas(_)),retractall(tarefa(_,_,_,_)),% apaga os dados existentes 
    cria_op_enc,
    produtosExistentes(TodosProdutos,NumeroProdutosExistentes),% obtem todos os produtos de todas as encomendas e o numero total
    asserta(tarefas(NumeroProdutosExistentes)),  %restringe a existentia de tantas tarefas como o numero de produtos existentes 
    cria_tarefas(TodosProdutos, NumeroProdutosExistentes,1),
    findall(tarefa(Id,TempoProcessamento,TempConc,PesoPenalizacao), tarefa(Id,TempoProcessamento,TempConc,PesoPenalizacao), Resultado),
    write(Resultado). % rever esta linha!!!!!
    
produtosExistentes(TodosProdutos,NumeroProdutosExistentes):-
    findall(Encomedas,encomenda(_,Encomedas),ConjuntoEncomendas),
    flatten(ConjuntoEncomendas,ListaEncomendas),
    obterProdutosEncomenda(ListaEncomendas,TodosProdutos),
    length(TodosProdutos,NumeroProdutosExistentes).
    	
  
obterProdutosEncomenda([],[]).
obterProdutosEncomenda([Encomenda|RestoEncomendas],[Produto|TodosProdutos]):-   				 
    obter_produto(Encomenda,Produto),
    obterProdutosEncomenda(RestoEncomendas,TodosProdutos).

obter_produto(e(Produto,_,_),Produto).
  

cria_tarefas(_,NumeroProdutosExistentes,NumeroProdutosExistentes).
cria_tarefas([Produto|RestantesProdutos], NumeroProdutosExistentes,Contador):-   
   		    IdTarefa is Contador,
    		cria_tarefaPorProduto(Produto,IdTarefa), % cria tarefa associada a cada produto ---- aqui entra o makespan!!!!
    		ContadorAuxiliar is Contador + 1,
    		cria_tarefas(RestantesProdutos, NumeroProdutosExistentes,ContadorAuxiliar).
    
    
cria_tarefaPorProduto(Produto,IdTarefa):-
    obterOperacoesDoProduto(Produto,OperacoesDoProduto),
    obterOperacoesOrdenadasPeloMaiorTempoExecucao(OperacoesDoProduto,ParesOrdenadosDecrescente),
    
    ParesOrdenadosDecrescente = [Operacao|OperacoesRestantes],
    obterTempoExecucaoPrimeiraOperacao(Operacao,MaiorTempoExecucao),
    
    op_prod_client(_,_,_,Produto,Cliente,DimensaoLote,TempoConclusao,_,_),
    
    TempoLote is (DimensaoLote *MaiorTempoExecucao),
    somaTempoExecaoRestante(OperacoesRestantes,SomaTempoExecaoRestante),
    Makespan is  TempoLote + SomaTempoExecaoRestante, 
    somaTemposPreparacao(ParesOrdenadosDecrescente,0,99999,TempoPreparacao),
    MakespanComTempoPreparacao is Makespan-TempoPreparacao,  
    prioridade_cliente(Cliente,Prioridade), 
    
    asserta(tarefa(IdTarefa, MakespanComTempoPreparacao,TempoConclusao, Prioridade)).


obterOperacoesDoProduto(Produto,OperacoesDoProduto):-findall((Texec-Op),op_prod_client(Op,_,_,Produto,_,_,_,_,Texec), OperacoesDoProduto).

%Obter uma lista de pares Operacao-tempo execucao ordenadas pelo tempo de execucao de forma decrescente
obterOperacoesOrdenadasPeloMaiorTempoExecucao(OperacoesDoProduto,ParesOrdenadosDecrescente):-
    sort(OperacoesDoProduto,ListaParesOrdenadosPelaKey), 
	reverse(ListaParesOrdenadosPelaKey,ParesOrdenadosDecrescente).
    

obterTempoExecucaoPrimeiraOperacao( (TempoExecucao-_),TempoExecucao).

somaTempoExecaoRestante([],0).
somaTempoExecaoRestante([(TempoExecucao-_)|Operacoes],SomaTempoExecaoRestante):-
    			somaTempoExecaoRestante(Operacoes,Tempo),
    			SomaTempoExecaoRestante is Tempo + TempoExecucao.


somaTemposPreparacao([],_,MenorTOcupacao,MenorTOcupacao).
somaTemposPreparacao([(_-Op)|ListaOperacoes], SomatorioTempoExecucao,MenorTO, TempoPreparacao):- 
	op_prod_client(Op,_,_,_,_,_,_,Tsetup,Texecucao),
    TOcupacao is SomatorioTempoExecucao - Tsetup,
    SomatorioTempoExecucao1 is SomatorioTempoExecucao+Texecucao,
    (TOcupacao<MenorTO,MenorTO1 is TOcupacao; MenorTO1 is MenorTO),
    somaTemposPreparacao(ListaOperacoes, SomatorioTempoExecucao1,MenorTO1, TempoPreparacao).






% ------------------------------------- heuristicas----------------------------
edd(Ind):-
	findall(Ind,tarefa(Ind,_,_,_),ListaTarefas),
	ordena_populacao(ListaTarefas,ListaTarefasOrdenada).
	

menorAtrasoEncomendaComPrioridades_SemShortestpath(Lm,T):-
                get_time(Ti),
                menorAtrasoEncomendaComPrioridades_SemShortestpath1(Lm,T),!,
                get_time(Tf),Tcomp is Tf-Ti,
                write('GERADO EM '),write(Tcomp),
                write(' SEGUNDOS'),nl.
 
menorAtrasoEncomendaComPrioridades_SemShortestpath1(Lm,T):- 
    					  listaOrdenadaSemShortestPath(Lm),
                          avalia(Lm,T).
                         
menorAtrasoEncomendaComPrioridades_Shortestpath(Lm,T):-
                get_time(Ti),
                menorAtrasoEncomendaComPrioridades_Shortestpath1(Lm,T),!,
                get_time(Tf),Tcomp is Tf-Ti,
                write('GERADO EM '),write(Tcomp),
                write(' SEGUNDOS'),nl. 
 
menorAtrasoEncomendaComPrioridades_Shortestpath1(Lm,T):- 
    				      listaOrdenadaShortestPath(Lm),
                          avalia(Lm,T).                      
                         
 
parPrioridadeTempoConclusaoSemShortestPath(IdTarefa,PesoPenalizacao-IdTarefa):-tarefa(IdTarefa,_,TConclusao,Prioridade), PesoPenalizacao is (TConclusao * ((15-Prioridade)/10)).
 
parPrioridadeTempoConclusaoShortestPath(IdTarefa,PesoPenalizacao-IdTarefa):-tarefa(IdTarefa,Makespan,_,Prioridade), PesoPenalizacao is (Makespan * ((15-Prioridade)/10)). 
 
listaOrdenadaSemShortestPath(S):- 
    		findall(Id, tarefa(Id,_,_,_), L),
			maplist(parPrioridadeTempoConclusaoSemShortestPath,L,X), 
    		keysort(X,Y),
    		pairs_values(Y,S).
listaOrdenadaShortestPath(S):- 
    		findall(Id, tarefa(Id,_,_,_), L), 
            maplist(parPrioridadeTempoConclusaoShortestPath,L,X),
    		keysort(X,Y),
    		pairs_values(Y,S).




    