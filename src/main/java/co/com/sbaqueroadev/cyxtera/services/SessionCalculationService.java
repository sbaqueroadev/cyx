
/* Archivo: TeacherServiceImpl.java
 * Fecha: 21/12/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva de Sergio Baquero Ariza
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de Sergio Baquero Ariza.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dará lugar a las sanciones
 * previstas en la Ley.
 */

package co.com.sbaqueroadev.cyxtera.services;

import co.com.sbaqueroadev.cyxtera.dao.SessionCalculationRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.model.SessionCalculationInterface;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * TeacherServiceImpl:  
 */
@Service
public class SessionCalculationService implements SessionCalculationInterface {

    private static final long SIX_MONTHS = 24*36*18000000;
    @Autowired
	private SessionCalculationRepository sessionCalculationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

	public SessionCalculationService() {
		super();
	}

    /*public List<MeasureAverage> getAverage(String doctorId, String pacientId, String illId, Long fr, Long t) {
        Date from = null, to = null;
        if(!Objects.isNull(fr) && !Objects.isNull(t)){
            from = new Date(fr);
            to = new Date(t);
        }
	    Criteria criteria = null;
        if(doctorId != null && !doctorId.isEmpty()){
            criteria = new Criteria("doctor").is(new DBRef("doctor",doctorId));
        }
        if(pacientId != null && !pacientId.isEmpty()){
            Criteria pacCriteria = new Criteria("pacient").is(new DBRef("pacient",pacientId));
            criteria = criteria == null ? pacCriteria : criteria.andOperator(pacCriteria);
        }
        if(illId != null && !illId.isEmpty()){
            Criteria illCriteria = new Criteria("sick").is(new DBRef("sick",illId));
            criteria = criteria == null ? illCriteria : criteria.andOperator(illCriteria);
        }
        if(!Objects.isNull(from) && !Objects.isNull(to)){

            Criteria dateCriteria = new Criteria().andOperator(Criteria.where("dateMeasured").lte(
                    new SimpleDateFormat("dd-MM-yyyy").format(to)),
            Criteria.where("dateMeasured").gte(
                    new SimpleDateFormat("dd-MM-yyyy").format(from)));
            criteria = criteria == null ? dateCriteria : criteria.andOperator(dateCriteria);
        }
        criteria = criteria != null? criteria:new Criteria();
        MatchOperation matchStage = Aggregation.match(criteria);
        GroupOperation group =
                Aggregation.group("doctor","pacient","sick","unit").count().as("count").avg("value").as("val");
        GroupOperation group1 =
                Aggregation.group("_id.pacient")
                        .push(
                                new BasicDBObject("_id",new BasicDBObject("sick","$_id.sick")
                                        .append("unit","$_id.unit"))
                                        .append("avgValue","$val"))
                        .as("sicks");

        Aggregation aggregation = newAggregation(matchStage,group,group1);
        AggregationResults<MeasureAverage> result = mongoTemplate.aggregate(aggregation, "measure"
                ,MeasureAverage.class);
	    return result.getMappedResults();
    }

    public List<MeasureAverageDay> getAverageByDay(String pacientId, Long startDate, Long finalDate) {
        Date from = null, to = null;
        Criteria criteria = null;
        if(pacientId != null && !pacientId.isEmpty()){
            Criteria pacCriteria = new Criteria("pacient").is(new DBRef("pacient",pacientId));
            criteria = criteria == null ? pacCriteria : criteria.andOperator(pacCriteria);
        }
        Criteria dateCriteria = null;
        from = startDate != null? new Date(startDate):null;
        to = finalDate != null? new Date(finalDate):null;
        if(!Objects.isNull(from) && !Objects.isNull(to)){
            dateCriteria = Criteria.where("date").lte(to).gte(from);
        }else{
            dateCriteria = Criteria.where("date").ne(null);
        }

        criteria = criteria != null? criteria:new Criteria();
        criteria.andOperator(dateCriteria);

        MatchOperation matchStage = Aggregation.match(criteria);
        ProjectionOperation project = Aggregation.project("id","sick","unit","option","value")
                .and(context -> {

                    Document doc = DateOperators.DateFromString.fromStringOf("dateMeasured").toDocument(context);
                    doc.get("$dateFromString", Document.class);
                    return doc;
                }).as("dateMeasured");
        ProjectionOperation project2 = Aggregation.project("id","sick","unit","option","value")
                .andExpression("dayOfWeek(  dateMeasured)").as("dateMeasured");
        GroupOperation group =
                Aggregation.group("sick","unit","dateMeasured","option")
                        //.push("$$ROOT").as("data")
                        .avg("value").as("avg");
        Aggregation aggregation = newAggregation(matchStage,project,project2,group);
        AggregationResults<MeasureAverageDay> result = mongoTemplate.aggregate(aggregation, "measure"
                ,MeasureAverageDay.class);
        return result.getMappedResults();
    }

    /* (non-Javadoc)
	 * @see co.com.sbaqueroadev.cyxtera.model.RoleInterface#findByName(java.lang.String)
	 */
	/*@Override
	public Calculation findById(String id) {
		return sessionCalculationRepository.findById(id).isPresent()? sessionCalculationRepository.findById(id).get():null;
	}

    @Override
    public JSONObject getAverage() {
	    JSONObject data = new JSONObject();
        GroupOperation groupByStateAndSumPop = group( "unit")
                .avg("value").as("avgValue");
        Aggregation aggregation = newAggregation(
                groupByStateAndSumPop);

        AggregationResults<Calculation> result = mongoTemplate.aggregate(
                aggregation, "measure", Calculation.class);
            //return new JSONObject(result.getRawResults().toJson());
            return new JSONObject("{'value':1.00}");

    }

    @Override
    public Calculation[] getAll() {
        JSONObject data = new JSONObject();

        GroupOperation groupByStateAndSumPop = group( "unit")
                .avg("value").as("avg");
        Aggregation aggregation = newAggregation(
                groupByStateAndSumPop);

        /*AggregationResults<Calculation> result = mongoTemplate.aggregate(
                aggregation, "measure", Calculation.class);*/
            //return new JSONObject(result.getRawResults().toJson());
    /*        return sessionCalculationRepository.findAll().toArray(new Calculation[]{});//new Calculation[]{};



    }

    @Override
    public Calculation save(Calculation calculation) {
	    calculation = sessionCalculationRepository.save(calculation);
        return calculation;
    }

    @Override
    public Calculation[] findByStatus(Calculation.Status status) {
	   return sessionCalculationRepository.findByStatus(status);
    }

    public JSONObject jsonOf(List<MeasureAverage> average) {
	    JSONArray json = new JSONArray();
	    JSONObject labels = new JSONObject();
	    labels.put("doctors",new JSONArray());
        labels.put("pacients",new JSONArray());
        labels.put("ills",new JSONArray());
        labels.put("units",new JSONArray());
	    for (MeasureAverage ma: average){
	        for(MeasureAverage.IllAverage sick : ma.getSicks()){
	            labels.put("ills",insertUniqueByName(labels.getJSONArray("ills"),sick.get_id().getSick() != null ? sick.get_id().getSick().getName() : "",sick.get_id().getSick() != null ? sick.get_id().getSick().getId() : ""));
                labels.put("units",insertUniqueByName(labels.getJSONArray("units"),sick.get_id().getUnit()!=null?sick.get_id().getUnit().getUnit():""
                        ,sick.get_id().getUnit() != null ? sick.get_id().getUnit().getId() : ""));
            }
            if(ma.get_id()!=null) {
                labels.put("doctors", insertUniqueByName(labels.getJSONArray("doctors"), ma.get_id().getDoctor() != null ? ma.get_id().getDoctor().getFirstName() + ma.get_id().getDoctor().getLastName() : ""
                        , ma.get_id().getDoctor() != null ? ma.get_id().getDoctor().getId() : null));
                labels.put("pacients", insertUniqueByName(labels.getJSONArray("pacients"), ma.get_id().getFirstName() + ma.get_id().getLastName()
                        , ma.get_id().getId()));
            }
        }
        for (MeasureAverage ma: average){
            JSONObject measure = new JSONObject();
            if(ma.get_id()!=null) {
                measure.put("name", ma.get_id().getFirstName() + " " + ma.get_id().getLastName());
                measure.put("id", ma.get_id().getId());
                measure.put("count", ma.getCount());
                measure.put("doctor", new JSONObject().put("name", ma.get_id().getDoctor() != null ? ma.get_id().getDoctor().getFirstName() + " " + ma.get_id().getDoctor().getLastName() : "")
                        .put("id", ma.get_id().getDoctor() != null ? ma.get_id().getDoctor().getId() : null));
            }
            JSONArray ills = arrayFromLabels(labels.getJSONArray("ills"));
            for(MeasureAverage.IllAverage sick : ma.getSicks()){
                JSONObject ill = new JSONObject();
                int index = findObjectByName(ills,sick.get_id().getSick()!=null?sick.get_id().getSick().getName():"");
                if(index < 0) {
                    ill.put("name", sick.get_id().getSick() != null ? sick.get_id().getSick().getName() : "");
                    ill.put("units", arrayFromLabels(labels.getJSONArray("units")));
                    ills.put(ill);
                    index = findObjectByName(ills,sick.get_id().getSick() != null ? sick.get_id().getSick().getName() : "");
                }
                if(!ill.has("id")) {
                    ills.getJSONObject(index).put("id", sick.get_id().getSick() != null ? sick.get_id().getSick().getId() : null);
                }
                if(!ill.has("units")){
                    ill.put("units",arrayFromLabels(labels.getJSONArray("units")));
                    ills.getJSONObject(index).put("units", arrayFromLabels(labels.getJSONArray("units")));
                }
                int indexU = findObjectByName(ill.getJSONArray("units"),sick.get_id().getUnit()!=null?sick.get_id().getUnit().getUnit():"");
                JSONObject unit = new JSONObject();
                if(indexU < 0){
                    unit.put("name",sick.get_id().getUnit()!=null?sick.get_id().getUnit().getUnit():"");
                    indexU = findObjectByName(ill.getJSONArray("units"),sick.get_id().getUnit()!=null?sick.get_id().getUnit().getUnit():"");
                }else{
                    unit =  ill.getJSONArray("units").getJSONObject(indexU);
                }
                unit.put("value",sick.getAvgValue());
                ills.getJSONObject(index).getJSONArray("units").put(indexU,unit);
            }
            for(int i = 0;i<ills.length();i++){
                JSONObject ill = ills.getJSONObject(i);
                if(!ill.has("units")){
                    ill.put("units",arrayFromLabels(labels.getJSONArray("units")));
                }
                for(int j = 0; j < ill.getJSONArray("units").length(); j++){
                    JSONObject unit = ill.getJSONArray("units").getJSONObject(j);
                    if(!unit.has("value")){
                        unit.put("value","--");
                        ill.getJSONArray("units").put(j,unit);
                        ills.put(i,ill);
                    }
                }
            }
            measure.put("ills",ills);
            json.put(measure);
        }

        JSONObject result = new JSONObject();
	    result.put("entity",json);
        return result;
    }



    private JSONArray arrayFromLabels(JSONArray ills) {
	    JSONArray result = new JSONArray();
	    for(Object ill: ills){
	        JSONObject i = new JSONObject();
	        i.put("name",((JSONObject)ill).get("name"));
            i.put("id",((JSONObject)ill).get("id"));
	        result.put(i);
        }
        return result;
    }

    private JSONArray insertUniqueById(JSONArray list, String s, String id) {
        if(id !=null ){
            int index = findObjectById(list,id);
            if(index < 0) {
                list.put(new JSONObject().put("name",s).put("id",id));    
            }
        }
        return list;
    }

    private JSONArray insertUniqueByName(JSONArray list, String s, String id) {
        int index = findObjectByName(list,s);
        if(index < 0) {
            list.put(new JSONObject().put("name",s).put("id",id));    
        }
        return list;
    }

    private int findObjectById(JSONArray array, String id) {
	    for (int i = 0;i<array.length();i++){
            if(array.get(i) instanceof JSONObject){
                if(array.getJSONObject(i).get("id")!=null){
                    if(array.getJSONObject(i).get("id").equals(id))
                      return i;
                }
            }else{
                if(array.getString(i).equals(id)){
                    return i;
                }
            }

        }
        return -1;
    }

    private int findObjectByName(JSONArray array, String name) {
	    for (int i = 0;i<array.length();i++){
            if(array.get(i) instanceof JSONObject){
                if(array.getJSONObject(i).get("name")!=null){
                    if(array.getJSONObject(i).get("name").equals(name))
                      return i;
                }
            }else{
                if(array.getString(i).equals(name)){
                    return i;
                }
            }

        }
        return -1;
    }

    public List<Calculation> getAllBetweenDatesToChart(String doctorId, String pacientId, String illId, Long fr, Long t){
	    Date sixMonthsAgo = new Date();
	    sixMonthsAgo.setTime(sixMonthsAgo.getTime()-(SIX_MONTHS));
        Criteria pacCriteria;
        Date from = null, to = null;
        if(!Objects.isNull(fr) && !Objects.isNull(t)){
            from = new Date(fr);
            to = new Date(t);
        }
        if(pacientId != null && !pacientId.isEmpty()){
            try {
                pacCriteria = new Criteria("pacient").is(new DBRef("pacient", new ObjectId(pacientId)));
            }catch (IllegalArgumentException e){
                pacCriteria = new Criteria("pacient").is(new DBRef("pacient", pacientId));
            }
        }else{
            pacCriteria = Criteria.where("pacient").ne(null);
        }
        Criteria illCriteria = null, dateCriteria = null;
        if(illId != null && !illId.isEmpty()){
            illCriteria = new Criteria("sick").is(new DBRef("sick",illId));
        }else{
            illCriteria = Criteria.where("sick").ne(null);
        }

        if(!Objects.isNull(from) && !Objects.isNull(to)){
            dateCriteria = new Criteria().andOperator(Criteria.where("dateMeasured").lte(
                    new SimpleDateFormat("dd-MM-yyyy").format(to)),
                    Criteria.where("dateMeasured").gte(
                            new SimpleDateFormat("dd-MM-yyyy").format(from)));

        }else{
            dateCriteria = Criteria.where("dateMeasured").ne(null);
        }
        Criteria criteria = new Criteria();
        criteria.andOperator(pacCriteria,illCriteria,dateCriteria);
        MatchOperation matchStage = Aggregation.match(criteria);
        Aggregation aggregation = newAggregation(matchStage
                ,sort(Sort.Direction.DESC, "lastModifiedDate")
                ,limit(10000)
                ,sort(Sort.Direction.ASC,"sick")
                ,sort(Sort.Direction.ASC,"date")
        );
        AggregationResults<Calculation> result = mongoTemplate.aggregate(aggregation, "measure"
                , Calculation.class);
        return result.getMappedResults();

    }

    public List<MeasureAllDay> getAllDayBetweenDatesToChart(String pacientId, Long fr, Long t){
        Date sixMonthsAgo = new Date();
        sixMonthsAgo.setTime(sixMonthsAgo.getTime()-(SIX_MONTHS));
        Criteria pacCriteria;
        Date from = null, to = null;
        if(!Objects.isNull(fr) && !Objects.isNull(t)){
            from = new Date(fr);
            to = new Date(t);
        }
        if(pacientId != null && !pacientId.isEmpty()){
            try {
                pacCriteria = new Criteria("pacient").is(new DBRef("pacient", new ObjectId(pacientId)));
            }catch (IllegalArgumentException e){
                pacCriteria = new Criteria("pacient").is(new DBRef("pacient", pacientId));
            }
        }else{
            pacCriteria = Criteria.where("pacient").ne(null);
        }
        Criteria dateCriteria = null;
        if(!Objects.isNull(from) && !Objects.isNull(to)){

            dateCriteria = Criteria.where("date").lte(to).gte(from);

        }else{
            dateCriteria = Criteria.where("dateMeasured").ne(null);
        }
        Criteria criteria = new Criteria();
        criteria.andOperator(pacCriteria, dateCriteria);


        MatchOperation matchStage = Aggregation.match(criteria);
        ProjectionOperation project = Aggregation.project("id","sick","unit","option","value","date")
                .and(context -> {

                    Document doc = DateOperators.DateFromString.fromStringOf("dateMeasured").toDocument(context);
                    doc.get("$dateFromString", Document.class);
                    return doc;
                }).as("dateMeasured");
        ProjectionOperation project2 = Aggregation.project("id","sick","unit","option","value","date")
                .andExpression("dayOfWeek(  dateMeasured)").as("dateMeasured");
        GroupOperation group =
                Aggregation.group("dateMeasured")
                        .push(new BasicDBObject("date","$date")
                                .append("option","$option")
                                .append("value","$value")).as("measures");
        Aggregation aggregation = newAggregation(matchStage,project,project2,group
                ,sort(Sort.Direction.ASC, "dateMeasured"));
              //  ,sort(Sort.Direction.ASC, "option")
                //,sort(Sort.Direction.ASC, "date"));
        AggregationResults<MeasureAllDay> result = mongoTemplate.aggregate(aggregation, "measure"
                , MeasureAllDay.class);
        return result.getMappedResults();

    }

    public JSONObject jsonOfAll(List<Calculation> data, String doctorId) {
        JSONArray json = new JSONArray();
        JSONObject labels = new JSONObject();
        labels.put("doctors",new JSONArray());
        labels.put("pacients",new JSONArray());
        labels.put("ills",new JSONArray());
        JSONObject measures = new JSONObject();
        for (Calculation ma: data){
            if(doctorId !=null ){
                if(!ma.getPacient().getSessionCalculation().getId().equals(doctorId)){
                    continue;
                }
            }
            if(ma.getPacient()!=null && ma.getSick()!=null){
                labels.put("doctors",insertUniqueById(labels.getJSONArray("doctors"),ma.getPacient().getSessionCalculation()!=null?ma.getPacient().getSessionCalculation().getFirstName()+" "+ma.getPacient().getSessionCalculation().getLastName():""
                        ,ma.getPacient().getSessionCalculation()!=null?ma.getPacient().getSessionCalculation().getId():null));
                labels.put("pacients",insertUniqueById(labels.getJSONArray("pacients"),ma.getPacient()!=null?ma.getPacient().getFirstName()+" "+ma.getPacient().getLastName():""
                        ,ma.getPacient()!=null?ma.getPacient().getId():null));
                labels.put("ills",insertUniqueById(labels.getJSONArray("ills"),ma.getSick()!=null?ma.getSick().getName():""
                        ,ma.getSick()!=null?ma.getSick().getId():null));
                String key = ma.getPacient().getFirstName()+" "
                        +ma.getPacient().getLastName().substring(0,1).toUpperCase()+". - "
                        +ma.getSick().getName();
                if(!measures.has(key) ||
                        (measures.has(key)
                            && !measures.getJSONObject(key).get("id").equals(ma.getPacient().getId()))){
                    if(measures.has(key)){
                        if(key.lastIndexOf(" -- ")>=0){
                            key = key.substring(0,key.lastIndexOf(" -- "));
                            int index = Integer.parseInt(key.substring(key.lastIndexOf(" -- ")+4));
                            key = key + " -- "+ (++index);
                        }else{
                            key = key + " -- 1";
                        }
                    }
                    measures.put(key,new JSONObject().put("data",new JSONArray()).put("id",
                            ma.getPacient().getId()));
                }

                measures.getJSONObject(key).getJSONArray("data").put(ma.getValue());
            }
        }
        JSONObject result = new JSONObject();
        result.put("entity",measures);
        result.put("labels",labels);
        return result;
    }

    public List<Calculation> getAlerts(String pacientId, Long startDate, Long finalDate) {
        Date sixMonthsAgo = new Date();
        sixMonthsAgo.setTime(sixMonthsAgo.getTime()-(SIX_MONTHS));
        Criteria pacCriteria;
        Date from = null, to = null;
        from = startDate != null? new Date(startDate):null;
        to = finalDate != null? new Date(finalDate):null;
        if(pacientId != null && !pacientId.isEmpty()){
            try {
                pacCriteria = new Criteria("pacient").is(new DBRef("pacient", new ObjectId(pacientId)));
            }catch (IllegalArgumentException e){
                pacCriteria = new Criteria("pacient").is(new DBRef("pacient", pacientId));
            }
        }else{
            pacCriteria = Criteria.where("pacient").ne(null);
        }
        Criteria dateCriteria = null;
        if(!Objects.isNull(from) && !Objects.isNull(to)){
            dateCriteria = Criteria.where("date").lte(to).gte(from);
        }else{
            dateCriteria = Criteria.where("date").ne(null);
        }
        Criteria rangeCriteria = new Criteria().andOperator(new Criteria().where("value").lte(70));
        Criteria criteria = new Criteria();
        criteria.andOperator(pacCriteria, dateCriteria, rangeCriteria);

        System.out.println(criteria.getCriteriaObject());

        MatchOperation matchStage = Aggregation.match(criteria);
        ProjectionOperation project = Aggregation.project("id","sick","unit","option","value","date");
        Aggregation aggregation = newAggregation(matchStage,project);
        AggregationResults<Calculation> result = mongoTemplate.aggregate(aggregation, "measure"
                , Calculation.class);
        List<Calculation> returnData = result.getMappedResults();
        Criteria rangeCriteria2 = new Criteria().andOperator(new Criteria().where("value").gte(130));
        Criteria criteria2 = new Criteria();
        criteria2.andOperator(pacCriteria, dateCriteria, rangeCriteria2);
        MatchOperation matchStage2 = Aggregation.match(criteria2);
        Aggregation aggregation2 = newAggregation(matchStage2,project);
        AggregationResults<Calculation> result2 = mongoTemplate.aggregate(aggregation2, "measure"
                , Calculation.class);
        //returnData.addAll(
        List<Calculation> returnData2 = result2.getMappedResults();
        ArrayList<Calculation> totalData = new ArrayList<>();
        totalData.addAll(returnData);
        totalData.addAll(returnData2);
        return totalData;
    }*/

    @Override
    public SessionCalculation addCalculation(SessionCalculation sessionCalculation, Calculation calculation) {
        sessionCalculation.addCalculation(calculation);
        return sessionCalculation;
    }

    @Override
    public SessionCalculation insert(SessionCalculation sessionCalculation) {
        return sessionCalculationRepository.insert(sessionCalculation);
    }

    @Override
    public Integer calculateResult(SessionCalculation sessionCalculation) throws OperandsException{
        Integer result = 0;
        int index = 0;
        for(Calculation item : sessionCalculation.getCalculations())
        {
            if (index > 0) {
                List<Integer> numbers = item.getNumbers();
                numbers.add(0, result);
                item.setNumbers(numbers);
            }
            result = item.calculate();
        }
        return result;
    }

    @Override
    public void update(SessionCalculationData sessionData) {
        sessionCalculationRepository.save(sessionData);
    }
}
