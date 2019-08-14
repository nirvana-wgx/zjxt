SELECT 
           * from (
           SELECT DISTINCT  ROW_NUMBER() OVER(ORDER BY ou.CREATEDATE ASC) AS rownum, 
           oup.PROJECTINFOID, 
           ou.CREATEDATE,
           ou.ID 
           FROM dbo.S_EPC_OUTUSER ou
			JOIN dbo.S_EPC_OUTUSER_PROJECT oup ON ou.ID = oup.S_EPC_OUTUSERID
		   
			WHERE ou.SYSUSERID = 'a81600f0-0bf4-42bd-a96c-ca162568f5a4') t 
			WHERE t.rownum <= 10
		and t.rownum >0


SELECT * FROM (SELECT DISTINCT PROJECTINFOID, ROW_NUMBER() OVER(ORDER BY PROJECTINFOID ASC) AS rownum FROM dbo.S_W_OBSUSER WHERE USERID = ? group by PROJECTINFOID ) as t


10628  3cfc43f0-29fc-495a-b67c-a72100b15a07

wangwu a81600f0-0bf4-42bd-a96c-ca162568f5a4


<insert id="insert" parameterType="vo.Category">

<selectKey resultType="java.lang.Short" order="BEFORE" keyProperty="id">

SELECT SEQ_TEST.NEXTVAL FROM DUAL

</selectKey>

insert into category (id,name_zh, parent_id,

show_order, delete_status, description

)

values (
#{id,jdbcType=NUMBER},

#{nameZh,jdbcType=VARCHAR},

#{parentId,jdbcType=NUMBER},

#{showOrder,jdbcType=NUMBER},

#{deleteStatus,jdbcType=NUMBER},

#{description,jdbcType=VARCHAR}

)

</insert>

//获取文档目录
http://localhost:8080/xtm-api/doc/getTree?projectId=a84100cb-abd1-4b03-876f-c3184d77ca6c&userId=USER0000

//获取文档列表
http://localhost:8080/xtm-api/doc/getFolderIdList?folderId=a84100cc-148e-4d91-8379-12c5e6905d73

//文档信息
http://localhost:8080/xtm-api/doc/uploadDocDetail?docId=a8d40115-e4ab-4322-90d7-9ff7ef04c977
//添加文档
http://localhost:8080/xtm-api/doc/addDocImg?docId=a8d40115-e4ab-4322-90d7-9ff7ef04c977&otherFiel=ss