--
-- Copyright 2010-2018 Boxfuse GmbH
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--         http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- Add new metadata row
INSERT INTO TABLE ${schema}.${table}
SELECT * FROM
(SELECT
    '${version_val}' version,
    ${installed_rank_val} installed_rank,
    '${description_val}' description,
    '${type_val}' type,
    '${script_val}' script,
    ${checksum_val} checksum,
    '${installed_by_val}' installed_by,
    CURRENT_TIMESTAMP() installed_on,
    ${execution_time_val} execution_time,
    ${success_val} success
) schemav;